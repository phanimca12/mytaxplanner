<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/global.css">
<link rel="stylesheet" href="css/home.css">


<script src="js/angularexternalJS/angular.min.js"></script>
 <script src="js/jqueryexternalJS/jquery.min.js"></script>
 <script src="js/jqueryexternalJS/popper.min.js"></script>
 <script src="js/bootstrapexternalJS/bootstrap.min.js"></script>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  
  
 <script src="js/angularinternalJS/home.js"></script>
 <script src="js/jqueryinternalJS/logout.js"></script>
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

<div class="jumbotron-fluid"  id="header"><h1><a class="w3schools-logo notranslate" href="//www.mytaxplanner.com">mytaxplanner<span class="dotcom">.com</span></a></h1>

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
        <li><a href="#"  onclick="doLogout();">logout</a></li>
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
    <li class="active" ><a data-toggle="tab" href="#home">Home</a></li>
    <li><a data-toggle="tab" href="#menu1">ITR-Request</a></li>
    <li><a data-toggle="tab" href="#menu2" ng-click="getAttachment('<%= session.getAttribute("customerName")%>')">Uploads/Downloads</a></li>
    <li><a data-toggle="tab" href="#menu3" ng-click="getUser('<%= session.getAttribute("customerName")%>')">ITR-Details</a></li>
  </ul>

  <div class="tab-content">
  
    <div id="home" class="tab-pane fade in active" >
          <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <div id="menu1" class="tab-pane fade" >
      <h3>Request Tax-Return Filing</h3>
      
     <form  id="ITR_Submit_Form" >
    
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
    <input type="button" value="Submit" id="submitRequest"  onclick="submitRequest()"  />            
                
      </form> 
      
      
    </div>
    
    <div id="menu2" class="tab-pane fade">
      
       
       <table class="table">
<thead class="thead-dark">
      <tr>
        <th>RequestID</th>
        <th>File Name</th>
       <!--  <th>Document Type</th> -->
        <th>Upload Date</th>
               
      </tr>
    </thead>
    <tbody>
  <tr ng-repeat="attachment in Attachments">
    <td>{{ attachment.requestID}}</td>
    <td>{{ attachment.file_name }}</td>
    <!-- <td>{{ attachment.file_type }}</td> -->
    <td>{{ attachment.req_Date }}</td>
    
     
  <td><a title="download" class="glyphicon glyphicon-download-alt" ng-click="GetAttachDownload($index)" ng-href="download?filename={{attachment.file_name }}&filepath={{attachment.file_path}}" ></a></td>
  </tr>
  </tbody>
</table>
   
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
    <td><span class="glyphicon glyphicon-trash"></span> <input type="button" class="btn btn-danger" value="Delete" ng-click="GetDetails($index)" /></td>
    <td><input type="button" value="Edit" class="btn btn-info btn" data-toggle="modal" data-target="#myModal" ng-click="getReqestID($index)" /><span class="glyphicon glyphicon-edit"></span></td>
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
          <form id="modifyITRForm">
           <div class="form-group">
    <p><label for="agent">RequestID:</label>
   <input type="text" ng-model="requestID" id="requestID" name="requestID">
      </p>
   
  </div><div class="form-group">
   <p><label for="agent">Assessment Year:</label>
   <select ng-model="selectedYear"  class="form-control" name="mfileyear" id="mfileyear" >
   <option ng-repeat="year  in Years" value="{{year}}">{{year}}</option>
</select></p>
   
  </div>
  <div class="form-group">
    <p><label for="agent">Upload Form16 & Other:</label>
   <input type="file" name="files" id="files" class="form-control" multiple />
   <div id="selectedFiles">
   
   
   </div>
   </p>

  </div>
              
   <p><input type="hidden"   name="userID" id="userID" value="<%= session.getAttribute("customerName")%>"   >
    <input type="submit" value="Submit"  class="btn btn-primary" id="modifyRequest" />   
  </form>
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



<div class="jumbotron-fluid"  id="footer"><p class="copyright">Copyright © 2019 mytaxplanner.com</p>
</div>

</body>
</html>