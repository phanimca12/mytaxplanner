package com.trs.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class MyLoginServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        
        
        String name;
        String pass;
		try {
			//JSONObject jObj=jObj= new JSONObject(sb.toString());	 
			name = request.getParameter("name");
			pass = request.getParameter("pass");
				
		        response.setContentType("text/html");  
		        PrintWriter out=response.getWriter();  
		        HttpSession session=request.getSession();
		        session.setAttribute("UserName",name);
		        
		     
		    RequestDispatcher requestDispatcher=   request.getRequestDispatcher("/Home.html");
		    requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	     
	
    
	}

}
