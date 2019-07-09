package com.trs.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trs.logger.FileLogger;

public class DownloadServlet extends HttpServlet
{
  Logger m_logger = FileLogger.getInstance();

  @Override
  public void doGet( final HttpServletRequest request, final HttpServletResponse response )
  {
    try
    {

      final String filename = request.getParameter( "filename" );
      final String filepath = request.getParameter( "filepath" );
      response.setContentType( "text/html" );
      final PrintWriter out = response.getWriter();

      response.setContentType( "APPLICATION/DOWNLOAD" );
      response.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );

      final FileInputStream fileInputStream = new FileInputStream( filepath + "/" + filename );

      int i;
      while ( ( i = fileInputStream.read() ) != -1 )
      {
        out.write( i );
      }
      fileInputStream.close();
      out.close();
    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, DownloadServlet.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );

    }
  }

}
