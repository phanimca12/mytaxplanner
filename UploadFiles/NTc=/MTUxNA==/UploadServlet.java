package com.trs.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig( fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 ) // 50MB
public class UploadServlet extends HttpServlet
{
  /**
   * Name of the directory where uploaded files will be saved, relative to the web application directory.
   */

  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException,
                                                                                             ServletException
  {
    final String mobilenumber = "9441054052";
    final String taxfilingyear = "20182019";
    final String SAVE_DIR = "uploadFiles";

    final InputStream reader = getClass().getClassLoader().getResourceAsStream( "Config.properties" );

    final Properties p = new Properties();
    p.load( reader );

    final String appPath = p.getProperty( "BASE_PATH" );
    // constructs path of the directory to save uploaded file
    final String savePath = appPath + File.separator + SAVE_DIR;

    // creates the save directory if it does not exists
    final File fileSaveDir = new File( savePath );
    if ( !fileSaveDir.exists() )
    {
      fileSaveDir.mkdir();
    }
    final String mobilefolderpath = savePath + File.separator + mobilenumber;
    final File mobileDir = new File( mobilefolderpath );
    if ( !mobileDir.exists() )
    {
      mobileDir.mkdir();
    }
    final String yearfolderpath = mobilefolderpath + File.separator + taxfilingyear;
    final File yearDir = new File( yearfolderpath );
    if ( !yearDir.exists() )
    {
      yearDir.mkdir();
    }

    for ( final Part part : request.getParts() )
    {
      String fileName = extractFileName( part );
      // refines the fileName in case it is an absolute path
      fileName = new File( fileName ).getName();

      System.out.println( "FilePath=" + yearfolderpath );
      part.write( yearfolderpath + File.separator + fileName );
    }

    final PrintWriter out = response.getWriter();
    out.write( "Successfully Uploaded" );

  }

  /**
   * Extracts file name from HTTP header content-disposition
   */
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

}
