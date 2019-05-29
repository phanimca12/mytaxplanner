package com.trs.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServ extends  HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		
		HttpSession session=request.getSession(false);
		   System.out.println("Hiiii"+session.getAttribute("name"));
		
		
	}

}
