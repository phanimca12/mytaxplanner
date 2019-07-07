package com.trs.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AgentLogoutServlet extends HttpServlet
{

  @Override
  public void doGet( final HttpServletRequest request, final HttpServletResponse response ) throws IOException
  {

    final HttpSession session = request.getSession();
    session.removeAttribute( "AgentName" );
    session.invalidate();
    response.sendRedirect( "agentlogin.html" );
  }

}
