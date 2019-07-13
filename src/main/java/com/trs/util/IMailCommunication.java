package com.trs.util;

import java.util.Properties;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMailCommunication
{
  String USERNAME = "noreply@mytaxfiler.co.in";
  String PASSWORD = "Tharuni_12#";
  String HOST     = "mail.mytaxfiler.co.in";
  String PORT     = "587";

  public Properties getEmailSMTPSettings();

  public Session getEmailSession();

  public void getForgotPassword( String emailID,
                                 Session session,
                                 final String fromEmailIDfinal,
                                 HttpServletRequest request,
                                 final HttpServletResponse respone );

}
