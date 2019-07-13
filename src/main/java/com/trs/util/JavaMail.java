package com.trs.util;

public class JavaMail
{

  /* public static void sendEmailWithAttachments( final String host,
                                               final String port,
                                               final String userName,
                                               final String password,
                                               final String toAddress,
                                               final String subject,
                                               final String message ) throws AddressException, MessagingException
  {
    // sets SMTP server properties
    final Properties properties = new Properties();
    properties.put( "mail.smtp.host", host );
    properties.put( "mail.smtp.port", port );
    properties.put( "mail.smtp.auth", "true" );
    // properties.put( "mail.smtp.starttls.enable", "true" );
    properties.put( "mail.user", userName );
    properties.put( "mail.password", password );
  
    // creates a new session with an authenticator
    final Authenticator auth = new Authenticator()
    {
      @Override
      public PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication( userName, password );
      }
    };
    final Session session = Session.getInstance( properties, auth );
  
    // creates a new e-mail message
    final Message msg = new MimeMessage( session );
  
    msg.setFrom( new InternetAddress( userName ) );
    final InternetAddress[] toAddresses = { new InternetAddress( toAddress ) };
    msg.setRecipients( Message.RecipientType.TO, toAddresses );
    msg.setSubject( subject );
    msg.setSentDate( new Date() );
  
    // creates message part
    final MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent( message, "text/html" );
  
    // creates multi-part
    final Multipart multipart = new MimeMultipart();
    multipart.addBodyPart( messageBodyPart );
  
        // adds attachments
    if ( attachFiles != null && attachFiles.length > 0 )
    {
      for ( final String filePath : attachFiles )
      {
        final MimeBodyPart attachPart = new MimeBodyPart();
    
        try
        {
          attachPart.attachFile( filePath );
        }
        catch ( final IOException ex )
        {
          ex.printStackTrace();
        }
    
        multipart.addBodyPart( attachPart );
      }
    }
    
    // sets the multi-part as e-mail's content
    msg.setContent( multipart );
  
    // sends the e-mail
    Transport.send( msg );
  
  }*/

  /**
   * Test sending e-mail with attachments
   */
  public static void main( final String[] args )
  {
    // SMTP info
    /* final String host = "mail.mytaxfiler.co.in";
    final String port = "587";
    final String mailFrom = "noreply@mytaxfiler.co.in";
    final String password = "Tharuni_12#";
    
    // message info
    final String mailTo = "phani.mca12@gmail.com";
    final String subject = "New email with attachments";
    final String message = "I have some attachments for you.";
    
    // attachments
    final String[] attachFiles = new String[3];
    attachFiles[ 0 ] = "e:/Test/Picture.png";
    attachFiles[ 1 ] = "e:/Test/Music.mp3";
    attachFiles[ 2 ] = "e:/Test/Video.mp4";
    
    try
    {
      sendEmailWithAttachments( host, port, mailFrom, password, mailTo, subject, message );
      System.out.println( "Email sent." );
    }
    catch ( final Exception ex )
    {
      System.out.println( "Could not send email." );
      ex.printStackTrace();
    }*/

  }
}
