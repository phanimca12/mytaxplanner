<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/global.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/pricing.css">


<script src="js/angularexternalJS/angular.min.js"></script>
 <script src="js/jqueryexternalJS/jquery.min.js"></script>
 <script src="js/jqueryexternalJS/popper.min.js"></script>
 <script src="js/bootstrapexternalJS/bootstrap.min.js"></script>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  
  
 <script src="js/angularinternalJS/agenthome.js"></script>
 <script src="js/jqueryinternalJS/agentlogout.js"></script>
 <script src="js/jqueryinternalJS/screensize.js"></script>
  <script src="js/jqueryinternalJS/fileupload.js"></script>
<script src="js/jqueryinternalJS/agenthomejquery.js"></script>

<title>mytaxplanner-Agent Home</title>

</head>
<%
ServletContext context=getServletContext();
response.setHeader("Cache-Control", context.getInitParameter("Cache-Control"));
response.setHeader("Pragma", context.getInitParameter("Pragma"));
response.setHeader("Expires", context.getInitParameter("Expires"));


if(session.getAttribute("AgentName")==null)
{
	
	response.sendRedirect("login.html");
	
}


%>
<body  ng-app="agenthomeApp" ng-controller="agenthomeCTRL">

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
session.getAttribute("AgentName")
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
    <li><a data-toggle="tab" href="#menu3" >ITR Request-Details</a></li>
     
  </ul>

  <div class="tab-content">
  
    <div id="home" class="tab-pane fade in active" >
         
        <!-- Left-aligned media object -->
        <br>
        
  <div class="media">
    <div class="media-left">
      <img src="images/avatar.png" class="media-object" style="width:60px">
    </div>
    <div class="media-body">
    
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
  </div>
  <hr> 
  <div ng-include="'H_subpage.html'"></div>
          
    </div>
    
    
   
    
    <div id="menu3" class="tab-pane fade" >

<br>
  <p><label for="agent">Select Status:</label>
   <select ng-model="selectedStatus"   name="status" id="status" ng-change="getAllRequest('<%= session.getAttribute("AgentName")%>')">
   <option ng-repeat="stat  in status" value="{{stat}}">{{stat}}</option>
</select></p>


<table class="table">
<thead class="thead-dark">
      <tr>
        <th>RequestID</th>
        <th>Date</th>
        <th>Assessment Year</th>
        <th>Status</th>
        <th>Comments</th>
        <th style="color:#5cb85c;font-style:bold">User Details</th>
        <th style="color:blue;font-style:bold">Attachments</th>
        <th style="color:orange;font-style:bold">Edit</th>
        
      </tr>
    </thead>
    <tbody>
  <tr ng-repeat="request in Requests">
    <td>{{ request.requestID}}</td>
    <td>{{ request.req_Date }}</td>
    <td>{{ request.filingYear }}</td>
    <td>{{ request.status }}</td>
    <td style="word-break:break-all;">{{ request.agentComments }}</td>
    <td><a class="glyphicon glyphicon-user" href="#" title="Click to View UserDetails" data-toggle="modal" data-target="#myModalUserInfo" ng-click="getuserDetails(request.userID)"> </a></td>
    <td><a class="glyphicon glyphicon-paperclip" href="#" title="Click to View Attachments" data-toggle="modal" data-target="#myModalAttachment" ng-click="getAttachment(request.requestID)"> </a></td>
    <td><a class="glyphicon glyphicon-edit" data-toggle="modal" data-target="#myModal" ng-click="getReqestID($index)"  href="#"></a>  </td>
  </tr>
  </tbody>
</table>
<!-- Modal-UserDetails -->
  <div class="modal fade" id="myModalUserInfo" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <p>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          </p>
          
          <br>
          <p>
          <h1 class="modal-title"><centre>Customer Information<centre></h1>
          </p>
        </div>
        <div class="modal-body">
          
   <div class="form-group">
    <p><label for="UserName">CustomerName:</label>
   <p >{{ cname }}</p>
      </p>
   
  </div>
  <div class="form-group">
    <p><label for="emailID">Email ID:</label>
   <p >{{ cmail }}</p>
      </p>
   
  </div>
   <div class="form-group">
    <p><label for="mobile">Mobile:</label>
   <p>{{ cmobile }}</p>
      </p>
   
  </div>
  
  
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<!-- Modal User Details -->
<!-- Modal-Attachments -->
  <div class="modal fade" id="myModalAttachment" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <p>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          </p>
          
          <br>
          <p>
          <h1 class="modal-title"><centre>Attachments<centre></h1>
          </p>
        </div>
        <div class="modal-body">
          <table class="table">
<thead class="thead-dark">
      <tr>
        <th>RequestID</th>
        <th>File Name</th>
       <!--  <th>Document Type</th> -->
        <th>Upload Date</th>
         <th style="color:blue;font-style:bold">Download</th>
               
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
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<!-- Modal Attachments -->

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
          <form id="modifyAgentITRForm">
           <div class="form-group">
    <p><label for="agent">RequestID:</label>
   <input type="text" ng-model="requestID" id="requestID" name="requestID">
      </p>
   
  </div><div class="form-group">
    <p><label for="agent">Select Status:</label>
   <select ng-model="MySelectedStatus"   name="mystatus" id="mystatus" >
   <option ng-repeat="mystat  in mystatus" value="{{mystat}}">{{mystat}}</option>
</select></p>
  </div>
   <div class="form-group">
  <label for="comment">Comment:</label>
  <textarea class="form-control" ng-model="agentcomment" rows="5" name="agentcomments" id="agentcomments" ></textarea>
</div> 

  <div class="form-group">
    <p><label for="agent">Upload Form16 & Other:</label>
   <input type="file" name="files" id="files" class="form-control" multiple  style="height:auto"/>
   <div id="selectedFiles">
   
   
   </div>
   </p>

  </div>
              
   <p><input type="hidden"   name="userID" id="userID" value="<%= session.getAttribute("AgentName")%>"   >
    <input type="submit" value="Submit"  class="btn btn-primary" id="updateRequest" />   
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