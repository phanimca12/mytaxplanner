<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
ServletContext context=getServletContext();
response.setHeader("Cache-Control", context.getInitParameter("Cache-Control"));
response.setHeader("Pragma", context.getInitParameter("Pragma"));
response.setHeader("Expires", context.getInitParameter("Expires"));

System.out.println("==="+context.getInitParameter("Cache-Control"));

if(session.getAttribute("customerName")==null)
{
	
	response.sendRedirect("login.html");
	
}


%>
WelCome 
<%= 
session.getAttribute("customerName")
%>
<form action="logout" method="GET">

<input type="submit" value="LOGOut">

</form>
</body>
</html>