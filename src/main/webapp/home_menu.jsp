<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/home_menu.css">
</head>
<body>
<nav class="navbar navbar-inverse"  id="mynav">
  <div class="container-fluid">
    <div class="navbar-header" >
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     <a class="navbar-brand"  data-toggle="tab" href="#home" style="color:#f4511e;"><span class="glyphicon glyphicon-home"></span> Home</a> 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
     
      <ul class="nav navbar-nav" id="myTab" >
    <li class="active" ><a data-toggle="tab" href="#home">Home</a></li>
    <li><a data-toggle="tab" href="#menu1" >ITR-Request</a></li>
    <li><a data-toggle="tab" href="#menu2" ng-click="getAttachment('<%= session.getAttribute("customerName")%>')">Uploads/Downloads</a></li>
    <li><a data-toggle="tab" href="#menu3" ng-click="getUser('<%= session.getAttribute("customerName")%>')">ITR-Details</a></li>
     <li><a data-toggle="tab" href="#menu4" >Contact Us</a></li>
       <li><a data-toggle="tab" href="#menu5" >Pricing & Payments</a></li>
  </ul>

     <!--  <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul> -->
    </div>
  </div>
</nav>
</body>
</html>