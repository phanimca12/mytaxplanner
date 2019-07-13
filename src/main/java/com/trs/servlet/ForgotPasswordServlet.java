package com.trs.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.mail.Session;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.trs.util.IMailCommunication;
import com.trs.util.IMailimpl;

public class ForgotPasswordServlet extends HttpServlet
{
  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException
  {
    final IMailCommunication icomm = new IMailimpl();

    final Session session = icomm.getEmailSession();
    final StringBuilder sb = new StringBuilder();

    final BufferedReader br = request.getReader();
    String str = null;
    while ( ( str = br.readLine() ) != null )
    {
      sb.append( str );
    }
    final JSONObject jObj = new JSONObject( sb.toString() );
    final String emailID = jObj.getString( "emailID" );

    icomm.getForgotPassword( emailID, session, icomm.USERNAME, request, response );

  }
}
