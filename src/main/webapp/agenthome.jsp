<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/global.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/panel.css">


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

<div class="jumbotron-fluid"  id="header"><h2><a class="w3schools-logo notranslate" href="www.mytaxfiler.co.in">mytaxfiler<span class="dotcom">.co.in</span></a></h2>

</div>
<div class="container-fluid">
<!-- Container-fluid  main -->

<!-- Mail Row 1 -->

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
        <p><a href="#"  onclick="doLogout();">logout</a></li>
       <!--  <p><a href="#">CSS</a></li>
        <p><a href="#">JavaScript</a></li>  -->                       
      </ul>
    </li>
  </ul>
</form>
</div>

</div>
<!-- End -->
<!-- Mail Row 2 -->
<div class="row">
<nav class="navbar navbar-inverse"  id="mynav">
  <div class="container-fluid">
    <div class="navbar-header" >
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     <a class="navbar-brand" id="home_section"  href="#" style="color:#f4511e;"><span class="glyphicon glyphicon-home"></span> Home</a> 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
     
      <ul class="nav navbar-nav" id="myTab" >
    <li ><a id="itr_section" href="#" ng-click="getAllRequest('<%= session.getAttribute("AgentName")%>')" >ITR Request-Details</a></li>
     <li ><a id="support_section" href="#"  >Support</a></li>
  </ul>

     <!--  <ul class="nav navbar-nav navbar-right">
        <p><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <p><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul> -->
    </div>
  </div>
</nav>
</div>
<!-- end-->
<!-- Mail Row 3-->

<div class=row>

<div class="col-sm-12" id="rightblock">

 <!-- Section-1-->
  <div class="Section_1">
  <div class="container-fluid">
  <div class="row">
  <div class="col-sm-12">
      <h3>Welcome to Mytaxfiler,</h3>
     
      <p style="font-family: Georgia, serif;">MyTaxFiler offers TaxConsultants/ITR-Filing Agents an Interface and record keeping system for their customers to place a request for ITR-Filing through online.</p>
    <p style="font-family: Georgia, serif;">Agents/Consultants need to sign-up as an Agent to get the registered Agent Code.Agent will be providing the Agent Code to his customers to file a request.</p>
  
     
     </div></div>
     <div class="row">
  <div class="col-sm-6">
    
      <div class="panel panel-default text-center">
        <div class="panel-heading">
          <h3>MyTaxFiler provides the following features to its Agents & customers.</h3>
        </div>
        <div class="panel-body">
             
  <p>Customer can place and upload form 16 through online.</li>
 <p>Customer can keep track of his ITR-filing request every year.</li>
 <p>Customer documents are secured in the database with SSL authentication.</li>
 <p>Customer can see the status of ITR-Filing in the portal and send queries to Agents/Consultants.</li>
 <p>Customer can find Acknowledgements in the portal once process is completed.</li>
 <p>Agents/Consultants can keep track of their customer filing process.</li>
 <p>Agents can send increase their customers through online processing.</li>
 <p>Agents communication with their customers will be improved.</li>
 <p>Automatic remainders are send to customers every year for ITR-Filing.</li>
 <p>Customers can upload and download documents .</li>
    
    </div>
    </div>
        </div>
        
      </div>
    </div>
 
    
 
  <hr> 

          
    </div>
    </div>
    
    
   <!-- Section-2-->
   
    <div class="Section_2">
  <div class="container-fluid">
   
   <br>
  <p><label for="agent">Select Status:</label>
   <select ng-model="selectedStatus"   name="status" id="status" ng-change="getRequestByStatus('<%= session.getAttribute("AgentName")%>')">
   <option ng-repeat="stat  in status" value="{{stat}}">{{stat}}</option>
</select></p>

 <div class="table-responsive">  
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
</div>

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

   
 <!-- - -->  
   </div>
    </div>
   
     <!-- Section-3-->
    
  <div class="Section_3">
  <div class="container-fluid bg-grey">
  <h2 class="text-center">CONTACT</h2>
  <div class="row">
    <div class="col-sm-5">
      <p>Contact us and we'll get back to you within 24 hours.</p>
      <p><span class="glyphicon glyphicon-map-marker"></span> Hyderabad, Telangana</p>
      <p><span class="glyphicon glyphicon-phone"></span> +91 9885678510</p>
      <p><span class="glyphicon glyphicon-envelope"></span> sridharcfp@mytaxfiler.co.in</p>
    </div>
    <div class="col-sm-7">
      <div class="row">
      <form id="supportForm">
        <div class="col-sm-6 form-group">
          <input class="form-control" id="sname" name="sname" placeholder="Name" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="semail" name="semail" placeholder="Email" type="email" required>
        </div>
      </div>
      <textarea class="form-control" id="scomments" name="scomments" placeholder="Comment" rows="5"></textarea><br>
      <div class="row">
        <div class="col-sm-12 form-group">
          <button class="btn btn-default pull-right" type="submit"  id="supportRequest">Send</button>
        </div>
      </div>
      </form>
    </div>
  </div>
</div> 
    </div>
    
    <!-- Section 4 -->
    </div></div>
    
 <!-- End-->   
 


<div class="jumbotron-fluid"  id="footer"><p class="copyright">Copyright © 2019 mytaxfiler.co.in</p>
</div>

</div>

</body>
</html>