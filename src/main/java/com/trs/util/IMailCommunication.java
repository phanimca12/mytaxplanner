package com.trs.util;

import java.util.Properties;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMailCommunication
{
  String USERNAME     = "noreply@mytaxfiler.co.in";
  String PASSWORD     = "Tharuni_12#";
  String HOST         = "mail.mytaxfiler.co.in";
  String PORT         = "587";
  String TOADDRESS    = "sridharcfp@gmail.com";
  String NOREPLYEMAIL = "noreply@mytaxfiler.co.in";

  public Properties getEmailSMTPSettings();

  public Session getEmailSession();

  public void getForgotPassword( String emailID,
                                 Session session,
                                 final String fromEmailIDfinal,
                                 HttpServletRequest request,
                                 final HttpServletResponse respone );

  public void sendRequestEmail( Long ReqID, String AgentEmailID, String cname, String filingYear );

  public void sendITRUpdateMail( final Long ReqID,
                                 final String custemailID,

                                 String status,
                                 String comments,

                                 final String fromEmailID );

  public void sendAgentCreatedMail( String AgentCode, String AgentEmailID, String AgentName, String fromEmailID );

  public void sendSupportRequestMail( String name, String FromEmailID, String message, String ToEmailID );
}
