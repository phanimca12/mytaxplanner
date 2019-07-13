package com.trs.util;

import java.util.Date;
import java.util.Properties;

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

public class IMailimpl implements IMailCommunication
{
  // Logger m_logger = FileLogger.getInstance();

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
        messageBodyPart.setContent( message + "</b>" + userPassword, "text/html" );

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

}
