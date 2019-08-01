package com.trs.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trs.dao.UserService;
import com.trs.logger.FileLogger;

public class IMailimpl implements IMailCommunication
{
  Logger m_logger = FileLogger.getInstance();

  public Properties getEmailSMTPSettings()
  {

    final Properties prop = new Properties();
    prop.put( "mail.smtp.host", IMailCommunication.HOST );
    prop.put( "mail.smtp.port", IMailCommunication.PORT );
    prop.put( "mail.smtp.auth", "true" );

    prop.put( "mail.user", IMailCommunication.USERNAME );
    prop.put( "mail.password", IMailCommunication.PASSWORD );
    return prop;
  }

  public Session getEmailSession()
  {

    final Properties prop = getEmailSMTPSettings();
    final Authenticator auth = new Authenticator()
    {
      @Override
      public PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication( IMailCommunication.USERNAME, IMailCommunication.PASSWORD );
      }
    };
    final Session session = Session.getInstance( prop, auth );
    return session;
  }

  public void getForgotPassword( final String toemailID,
                                 final Session session,
                                 final String fromEmailID,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response )
  {
    // creates a new e-mail message
    final String subject = "Forgot Email/Password";
    final String message = "Please find new passowrd";
    final Message msg = new MimeMessage( session );
    final UserService userService = new UserService();
    final String userPassword = userService.getUserPassword( toemailID );
    try
    {
      if ( userPassword != null )
      {

        msg.setFrom( new InternetAddress( fromEmailID ) );
        final InternetAddress[] toAddresses = { new InternetAddress( toemailID ) };
        msg.setRecipients( Message.RecipientType.TO, toAddresses );
        msg.setSubject( subject );
        msg.setSentDate( new Date() );

        // creates message part
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent( message + "-" + userPassword, "text/html" );

        // creates multi-part
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart( messageBodyPart );
        // sets the multi-part as e-mail's content
        msg.setContent( multipart );

        // sends the e-mail
        Transport.send( msg );
        response.setContentType( "text/plain" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write( "Pass" );
      }
      else
      {
        response.setContentType( "text/plain" );
        response.setCharacterEncoding( "UTF-8" );
        response.getWriter().write( "Fail" );
      }
    }
    catch ( final Exception e )
    {
      /* m_logger.log( Level.ALL, IMailimpl.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );*/
    }
  }

  public void sendRequestEmail( final Long ReqID,
                                final String AgentEmailID,
                                final String cname,
                                final String filingYear )
  {
    final Session session = getEmailSession();

    // creates a new e-mail message
    final String subject = "ITR Request Submitted by Customer-" + cname;
    final String message = "<HTML><BODY><h1>The Follow User has submitted new ITR Request</h1><br><br>Customer Name:"
        + cname + "<br>" + "Filing Year:" + filingYear + "<br>" + "Request ID:" + ReqID + "<br></BODY></HTML>";
    final Message msg = new MimeMessage( session );

    try
    {
      if ( ReqID != null )
      {

        msg.setFrom( new InternetAddress( IMailCommunication.NOREPLYEMAIL ) );
        final InternetAddress[] toAddresses = { new InternetAddress( AgentEmailID ) };
        msg.setRecipients( Message.RecipientType.TO, toAddresses );
        msg.setSubject( subject );
        msg.setSentDate( new Date() );

        // creates message part
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent( message, "text/html" );

        // creates multi-part
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart( messageBodyPart );
        // sets the multi-part as e-mail's content
        msg.setContent( multipart );

        // sends the e-mail
        Transport.send( msg );

      }

    }
    catch ( final Exception e )
    {
      /* m_logger.log( Level.ALL, IMailimpl.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );*/
    }

  }

  public void sendITRUpdateMail( final Long ReqID,
                                 final String custemailID,

                                 final String status,
                                 final String comments,
                                 final String fromEmailID )
  {
    final Session session = getEmailSession();

    // creates a new e-mail message
    final String subject = "ITR has been modified by Agent";
    final String message = "<HTML><BODY><h3>ITR Request has been updated by Agent</h3><br><br>" + "<br>"

        + "<p>Request ID:"
        + ReqID
        + "</p"
        + "<br>"
        + "<p>Status:"
        + status
        + "</p>"
        + "<br>"
        + "<p>Comments:"
        + comments
        + "</p>"
        + "</BODY></HTML>";
    final Message msg = new MimeMessage( session );
    final UserService userService = new UserService();
    final String userPassword = userService.getUserPassword( custemailID );
    try
    {
      if ( userPassword != null )
      {

        msg.setFrom( new InternetAddress( fromEmailID ) );
        final InternetAddress[] toAddresses = { new InternetAddress( custemailID ) };
        msg.setRecipients( Message.RecipientType.TO, toAddresses );
        msg.setSubject( subject );
        msg.setSentDate( new Date() );

        // creates message part
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent( message, "text/html" );

        // creates multi-part
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart( messageBodyPart );
        // sets the multi-part as e-mail's content
        msg.setContent( multipart );

        // sends the e-mail
        Transport.send( msg );

      }

    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, IMailimpl.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );
    }

  }

  public void sendAgentCreatedMail( final String AgentCode,
                                    final String AgentEmailID,
                                    final String AgentName,
                                    final String fromEmailID )
  {
    final Session session = getEmailSession();

    // creates a new e-mail message
    final String subject = "Agent Registered Successfully !";
    final String message = "<HTML><BODY><p>Dear &nbsp;" + AgentName
        + ",</p>"

        + "<p>Congratulations ! Your account has been created successfuly.Please find your Agent Code below."
        + "</p>"

        + "<p>Agent Code:"
        + AgentCode
        + "</p>"
        + "<br>"
        + "<p>Regards</p>"
        + "<p>mytaxfiler.co.in</p>"

        + "</BODY></HTML>";
    final Message msg = new MimeMessage( session );
    final UserService userService = new UserService();

    try
    {
      if ( AgentEmailID != null )
      {

        msg.setFrom( new InternetAddress( fromEmailID ) );
        final InternetAddress[] toAddresses = { new InternetAddress( AgentEmailID ) };
        msg.setRecipients( Message.RecipientType.TO, toAddresses );
        msg.setSubject( subject );
        msg.setSentDate( new Date() );

        // creates message part
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent( message, "text/html" );

        // creates multi-part
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart( messageBodyPart );
        // sets the multi-part as e-mail's content
        msg.setContent( multipart );

        // sends the e-mail
        Transport.send( msg );

      }

    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, IMailimpl.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );
    }

  }

  public void sendSupportRequestMail( final String name,
                                      final String FromEmailID,
                                      final String message,
                                      final String ToEmailID )
  {
    final Session session = getEmailSession();

    // creates a new e-mail message
    final String subject = "Agent Posted a Query !";
    final String body = "<HTML><BODY><p>" + message
        + "</p>"

        + "<br>"
        + "<p>Regards</p>"
        + "<p>"
        + name
        + "</p>"

        + "</BODY></HTML>";
    final Message msg = new MimeMessage( session );
    final UserService userService = new UserService();

    try
    {
      if ( FromEmailID != null )
      {

        msg.setFrom( new InternetAddress( FromEmailID ) );
        final InternetAddress[] toAddresses = { new InternetAddress( ToEmailID ) };
        msg.setRecipients( Message.RecipientType.TO, toAddresses );
        msg.setSubject( subject );
        msg.setSentDate( new Date() );

        // creates message part
        final MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent( message, "text/html" );

        // creates multi-part
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart( messageBodyPart );
        // sets the multi-part as e-mail's content
        msg.setContent( multipart );

        // sends the e-mail
        Transport.send( msg );

      }

    }
    catch ( final Exception e )
    {
      m_logger.log( Level.ALL, IMailimpl.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );
    }

  }

}
