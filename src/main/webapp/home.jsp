<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/global.css">
<link rel="stylesheet" href="css/mylogo.css">
<link rel="stylesheet" href="css/home.css">

<script src="js/angularexternalJS/angular.min.js"></script>


   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 <script src="js/angularinternalJS/home.js"></script>
 <script src="js/jqueryinternalJS/logoutJS.js"></script>
 <script src="js/jqueryinternalJS/screensize.js"></script>
  <script src="js/jqueryinternalJS/fileupload.js"></script>
<script src="js/jqueryinternalJS/PageLoad.js"></script>

<title>mytaxplanner-home</title>

</head>
<%
ServletContext context=getServletContext();
response.setHeader("Cache-Control", context.getInitParameter("Cache-Control"));
response.setHeader("Pragma", context.getInitParameter("Pragma"));
response.setHeader("Expires", context.getInitParameter("Expires"));


if(session.getAttribute("customerName")==null)
{
	
	response.sendRedirect("login.html");
	
}


%>
<body  ng-app="homeApp" ng-controller="homeCTRL">
<div class="jumbotron-fluid" >
<div>
<a class="mytaxplanner-logo" href="">mytaxplanner<span class="dotcom">.com</span></a>
</div>
</div>
<div class="container-fluid">
<div class="row">
<div class="col-sm-12" id="toprow">

<form id="headerform">


<ul class="nav nav-pills" role="tablist" id="user">
           
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
     
<%= 
session.getAttribute("customerName")
%><span class="caret"></span></a>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#"  onclick="callServlet();">logout</a></li>
       <!--  <li><a href="#">CSS</a></li>
        <li><a href="#">JavaScript</a></li>  -->                       
      </ul>
    </li>
  </ul>
</form>
</div>

</div>
<div class="row">

<div class="col-sm-12" id="rightblock">

 <ul class="nav nav-tabs" id="myTab">
    <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
    <li><a data-toggle="tab" href="#menu1">ITR-Request</a></li>
    <li><a data-toggle="tab" href="#menu2">Uploads/Downloads</a></li>
    <li><a data-toggle="tab" href="#menu3">ITR-Details</a></li>
  </ul>

  <div class="tab-content">
  
    <div id="home" class="tab-pane fade in active" >
          <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <div id="menu1" class="tab-pane fade" >
      <h3>Request Tax-Return Filing</h3>
      
     <form action="returnfilingreq" method="post" enctype="multipart/form-data">
    
    <p><label for="agent">Select Agent:</label>
   <select ng-model="selectedName"  class="form-control" name="agcode" id="agcode">
   <option ng-repeat="Agent in Agents" value="{{Agent.agentCode}}">{{Agent.agentCode}}</option>
</select></p>
       
    <p><label for="agent">Assessment Year:</label>
   <select ng-model="selectedYear"  class="form-control" name="fileyear" id="fileyear">
   <option ng-repeat="year  in Years" value="{{year}}">{{year}}</option>
</select></p>
    
    <p><label for="agent">Upload Form16 & Other:</label>
   <input type="file" name="files" id="files" class="form-control" multiple />
   <div id="selectedFiles">
   
   
   </div>
   </p>
      <p><input type="hidden"   name="userID" id="userID" value="<%= session.getAttribute("customerName")%>"   >  
   
    <input type="submit" value="Submit"  onclick="submitRequest()"  />            
                
      </form> 
      
      
    </div>
    
    <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
         <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
   
    </div>
    
    <div id="menu3" class="tab-pane fade" >


<table class="table">
<thead class="thead-dark">
      <tr>
        <th>RequestID</th>
        <th>Date</th>
        <th>Assessment Year</th>
        <th>Status</th>
        <th>Comments</th>
        
      </tr>
    </thead>
    <tbody>
  <tr ng-repeat="request in Requests">
    <td>{{ request.requestID}}</td>
    <td>{{ request.req_Date }}</td>
    <td>{{ request.filingYear }}</td>
    <td>{{ request.status }}</td>
    <td>{{ request.agentComments }}</td>
    <td><input type="button" value="delete" ng-click="GetDetails($index)" /></td>
    <td><input type="button" value="Edit" class="btn btn-info btn" data-toggle="modal" data-target="#myModal" ng-click="getReqestID($index)" /></td>
  </tr>
  </tbody>
</table>


<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <p>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          </p>
          
          <br>
          <p>
          <h1 class="modal-title"><centre>ITR Update<centre></h1>
          </p>
        </div>
        <div class="modal-body">
          
           <div class="form-group">
    <p><label for="agent">RequestID:</label>
   <input type="text" ng-model="requestID">
      </p>
   
  </div><div class="form-group">
    <p><label for="agent">Assessment Year:</label>
   <select ng-model="selectedYear"   ng-options="year for year in Years" class="form-control">
  
</select></p>
   
  </div>
  <div class="form-group">
    <p><label for="agent">Upload Form16 & Other:</label>
   <input type="file" name="files" id="files" class="form-control" multiple />
   <div id="selectedFiles">
   
   
   </div>
   </p>

  </div>
              
   
    <input type="submit" value="Submit"  class="btn btn-primary/>   
  
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<!-- Modal -->

    </div>
  </div>
</div>
</div>

</div>



<div class="jumbotron-fluid" >
<div>
<a class="mytaxplanner-logo" href="//www.w3schools.com">mytaxplanner<span class="dotcom">.com</span></a>
</div>
</div>

</body>
</html>