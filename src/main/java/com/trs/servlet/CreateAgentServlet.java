package com.trs.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;
import org.json.JSONObject;

import com.trs.dao.AgentService;
import com.trs.dao.IAgentService;
import com.trs.logger.FileLogger;
import com.trs.model.Agent;
import com.trs.util.HibernateSessionCnf;
import com.trs.util.IMailCommunication;
import com.trs.util.IMailimpl;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class CreateAgentServlet extends HttpServlet
{
  Logger m_logger = FileLogger.getInstance();

  public static void createAgent( final String aname,
                                  final String aemail,
                                  final String apassword,
                                  final String amobile,
                                  final String country,
                                  final String state,
                                  final String city,
                                  final String desc )
  {
    final IUtility util = new Utility();
    final Agent createAgent = new Agent();
    final IMailCommunication imail = new IMailimpl();
    final String AgentCode = "A" + util.getRandomNumber();

    createAgent.setName( aname );
    createAgent.setEmailID( aemail );
    createAgent.setPassword( apassword );
    createAgent.setCreationDate( util.getCurrentDateTime() );
    createAgent.setMobile( amobile );
    createAgent.setCountry( country );
    createAgent.setState( state );
    createAgent.setCity( city );
    createAgent.setAgentDescription( desc );
    createAgent.setAgentCode( AgentCode );

    final Transaction t = HibernateSessionCnf.getSession().beginTransaction();
    HibernateSessionCnf.getSession().save( createAgent );
    t.commit();
    imail.sendAgentCreatedMail( AgentCode, aemail, aname, imail.NOREPLYEMAIL );
  }

  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response )
  {

    final StringBuilder sb;
    final BufferedReader br;
    String str = null;

    try
    {
      sb = new StringBuilder();
      br = request.getReader();
      while ( ( str = br.readLine() ) != null )
      {
        sb.append( str );
      }
      final JSONObject jObj = new JSONObject( sb.toString() );

      final String aname = jObj.getString( "aname" );
      final String aemail = jObj.getString( "aemail" );
      final String apassword = jObj.getString( "apassword" );
      final String amobile = jObj.getString( "amobile" );

      final String country = "India";
      final String state = "Telangana";
      final String city = "Hyderabad";
      final String desc = "Certified ITR Filing Agent";

      final IAgentService user = new AgentService();

      if ( !user.isAgentrExist( aemail, amobile ) )
      {
        createAgent( aname, aemail, apassword, amobile, country, state, city, desc );

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
      m_logger.log( Level.ALL, CreateUserServlet.class.getName() + "\t" + e.getMessage(),
                    new IOException( "Internal server error" ) );

    }
  }
}
