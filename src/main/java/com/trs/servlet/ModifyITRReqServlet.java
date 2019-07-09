package com.trs.servlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trs.dao.IReturnFilingRequest;
import com.trs.dao.ReturnFilingService;
import com.trs.dao.UserService;
import com.trs.logger.FileLogger;
import com.trs.model.AttachmentDetails;
import com.trs.util.IUtility;
import com.trs.util.Utility;

@MultipartConfig( fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 ) // 50MB
public class ModifyITRReqServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  Logger                    m_logger         = FileLogger.getInstance();

  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException,
                                                                                             ServletException
  {
    try
    {
      final UserService service = new UserService();
      final IReturnFilingRequest ITR = new ReturnFilingService();

      final int UserID = service.getUserID( request.getParameter( "userID" ) );

      final Properties p = getProperties();
      final long REQID = Long.parseLong( request.getParameter( "requestID" ) );
      final String FilingYear = request.getParameter( "mfileyear" );

      final String SAVE_DIR = p.getProperty( "UPLOAD_FOLDER" );
      final String appPath = System.getProperty( "catalina.base" ) + "/" + "webapps";

      if ( ITR.modifyITR( FilingYear, REQID ) )

      {

        final String DOCUMENT_PATH = createFolder( String.valueOf( UserID ), String.valueOf( REQID ), SAVE_DIR,
                                                   appPath );

        storeDocument( request, response, DOCUMENT_PATH, REQID, UserID );
        response.setContentType( "text/plain" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write( "Modified ITR Filing Request Year  Successfully !" );
      }

      else
      {
        response.setContentType( "text/plain" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write( "ITR Modification failed !" );

      }
    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, ModifyITRReqServlet.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );
    }

  }

  public static String createFolder( final String uid, final String REQID, final String SAVE_DIR, final String appPath )
  {
    final String savePath = appPath + File.separator + SAVE_DIR;

    // creates the save directory if it does not exists
    final File fileSaveDir = new File( savePath );
    if ( !fileSaveDir.exists() )
    {
      fileSaveDir.mkdir();
    }
    final String mobilefolderpath = savePath + File.separator + uid;
    final File mobileDir = new File( mobilefolderpath );
    if ( !mobileDir.exists() )
    {
      mobileDir.mkdir();
    }
    final String yearfolderpath = mobilefolderpath + File.separator + REQID;
    final File yearDir = new File( yearfolderpath );
    if ( !yearDir.exists() )
    {
      yearDir.mkdir();
    }

    return yearfolderpath;
  }

  public void saveAttachment( String DOCUMENT_PATH,
                              final String filename,
                              final long ReqID,
                              final int UserID,
                              final AttachmentDetails attachmentDetails )
  {
    DOCUMENT_PATH = DOCUMENT_PATH.replace( "\\", "/" );
    final IUtility util = new Utility();
    attachmentDetails.setRequestID( ReqID );
    attachmentDetails.setUserID( UserID );
    attachmentDetails.setReq_Date( util.getCurrentDateTime() );
    attachmentDetails.setFile_name( filename );
    attachmentDetails.setFile_type( "file_type" );
    attachmentDetails.setFile_path( DOCUMENT_PATH );

    final Session session = util.getHibernateSessionObj();
    final Transaction t = session.beginTransaction();
    session.save( attachmentDetails );
    t.commit();
  }

  private String extractFileName( final Part part )
  {
    final String contentDisp = part.getHeader( "content-disposition" );
    final String[] items = contentDisp.split( ";" );
    for ( final String s : items )
    {
      if ( s.trim().startsWith( "filename" ) )
      {
        return s.substring( s.indexOf( "=" ) + 2, s.length() - 1 );
      }
    }
    return "";
  }

  public void storeDocument( final HttpServletRequest request,
                             final HttpServletResponse response,
                             final String DOCUMENT_PATH,
                             final long ReqID,
                             final int UserID ) throws IOException, ServletException
  {
    AttachmentDetails attachmentDetails = null;
    for ( final Part part : request.getParts() )
    {
      String fileName = extractFileName( part );
      // refines the fileName in case it is an absolute path
      fileName = new File( fileName ).getName();
      if ( !fileName.isEmpty() )
      {
        attachmentDetails = new AttachmentDetails();
        part.write( DOCUMENT_PATH + File.separator + fileName );
        saveAttachment( DOCUMENT_PATH, fileName, ReqID, UserID, attachmentDetails );
      }
    }
  }

  public static Properties getProperties() throws IOException
  {

    final File file = new File( System.getProperty( "catalina.base" ) + "/" + "webapps" + "/" + "Config.properties" );
    final FileReader reader = new FileReader( file );
    final Properties prop = new Properties();
    prop.load( reader );

    return prop;

  }
}
