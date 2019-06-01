package com.trs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyLoginServlet extends HttpServlet
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void doPost( final HttpServletRequest request, final HttpServletResponse response ) throws IOException,
                                                                                             ServletException
  {

    final String name = request.getParameter( "uname" );
    final String pass = request.getParameter( "pswd" );
    final HttpSession session = request.getSession();
    session.setAttribute( "customerName", name );
    System.out.println( "name=" + name );
    System.out.println( "password=" + pass );
    request.setAttribute( "uname", "Phani" );
    response.sendRedirect( "home.jsp" );

  }

}
