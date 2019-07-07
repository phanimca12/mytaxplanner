package com.trs.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.trs.dao.AgentService;
import com.trs.dao.IAgentService;

public class agentloginservlet extends HttpServlet
{
  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException,
                                                                                             ServletException
  {
    final StringBuilder sb = new StringBuilder();

    final BufferedReader br = request.getReader();
    String str = null;
    while ( ( str = br.readLine() ) != null )
    {
      sb.append( str );
    }
    final JSONObject jObj = new JSONObject( sb.toString() );
    final String name = jObj.getString( "name" );
    final String pass = jObj.getString( "pass" );
    /*System.out.println( "name=" + name );
    System.out.println( "password=" + pass )*/;

    final IAgentService validate = new AgentService();

    if ( validate.isAuthorizedAgent( name, pass ) )
    {
      final HttpSession session = request.getSession();
      session.setAttribute( "AgentName", name );
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

}
