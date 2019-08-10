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

import com.trs.dao.IUserService;
import com.trs.dao.UserService;
import com.trs.logger.FileLogger;
import com.trs.model.User;
import com.trs.util.HibernateSessionCnf;
import com.trs.util.IUtility;
import com.trs.util.Utility;

public class CreateUserServlet extends HttpServlet
{
  Logger m_logger = FileLogger.getInstance();

  public static void createUser( final String emailID, final String mobile, final String name, final String password )
  {
    final IUtility util = new Utility();
    final User createUser = new User();
    createUser.setName( name );
    createUser.setEmailID( emailID );
    createUser.setPassword( password );
    createUser.setCreationDate( util.getCurrentDateTime() );
    createUser.setMobile( mobile );

    final Transaction t = HibernateSessionCnf.getSession().beginTransaction();
    HibernateSessionCnf.getSession().save( createUser );
    t.commit();

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
      final String emailID = jObj.getString( "emailID" );
      final String mobile = jObj.getString( "mobile" );
      final String name = jObj.getString( "name" );
      final String pass = jObj.getString( "pass" );

      final IUserService user = new UserService();

      if ( !user.isUserExist( emailID, mobile ) )
      {
        createUser( emailID, mobile, name, pass );
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
