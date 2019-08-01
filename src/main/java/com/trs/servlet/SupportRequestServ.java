package com.trs.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trs.logger.FileLogger;
import com.trs.util.IMailCommunication;
import com.trs.util.IMailimpl;

public class SupportRequestServ extends HttpServlet
{
  Logger m_logger = FileLogger.getInstance();

  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException,
                                                                                             ServletException
  {
    try
    {

      final String name = request.getParameter( "sname" );
      final String FromEmail = request.getParameter( "smail" );
      final String message = request.getParameter( "scomments" );
      final IMailCommunication icom = new IMailimpl();

      icom.sendSupportRequestMail( name, FromEmail, message, icom.TOADDRESS );

      response.setContentType( "text/plain" );
      response.setCharacterEncoding( "UTF-8" );
      response.getWriter().write( "Query Posted Successfully !" );

    }
    catch ( final Exception e )
    {

      m_logger.log( Level.ALL, ModifyAgentITRRequestServlet.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );
    }
  }
}
