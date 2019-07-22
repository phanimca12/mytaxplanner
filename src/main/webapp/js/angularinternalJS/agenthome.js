var app = angular.module('agenthomeApp', []);

app.controller('agenthomeCTRL',  [ '$scope', '$http', '$window',function($scope, $http,$window) {
	
	
	$scope.Agents = [];
	$scope.Requests = [];
	$scope.Attachments = [];
	$scope.UserInfo = [];
	$scope.status = ["pending", "inprogress","complete", "All"];
	$scope.mystatus = ["pending", "inprogress","complete"];
	 $scope.Years = ["2015-2016", "2016-2017","2017-2018", "2018-2019"];
	 
	
	 
	 $scope.getSelectedValue=function()
	 {
		 alert($scope.selectedStatus);
		 
	 };
	$scope.getRequestByStatus=function(AgentName)
	{
	       //alert(AgentName +"status="+$scope.selectedStatus);
		 $http({
             url : '/v1/returnfilingrequest/requestdetails/'+ AgentName+'/'+$scope.selectedStatus,
             method : "GET",
             	 headers: {
             	        "Content-Type": "application/json",
             	        "Accept": "application/json"
             	    }
         }).then(function mySuccess(response) {
         	
       	
         	$scope.Requests=response.data;
         	
         
         }, function myError(response) {
           $scope.message = response.statusText;
         });
	};
	
	//
	
	$scope.getAllRequest=function(AgentName)
	{
	    var status="All";
		 $http({
             url : '/v1/returnfilingrequest/requestdetails/'+ AgentName+'/'+status,
             method : "GET",
             	 headers: {
             	        "Content-Type": "application/json",
             	        "Accept": "application/json"
             	    }
         }).then(function mySuccess(response) {
         	
       	
         	$scope.Requests=response.data;
         	
         
         }, function myError(response) {
           $scope.message = response.statusText;
         });
	};
	
	
	
	
	
	//
	$scope.getAttachment=function(requestID)
	{
	     
	      
		 $http({
             url : '/v1/attachments/downloadbyreqID/'+ requestID,
             method : "GET",
             	 headers: {
             	        "Content-Type": "application/json",
             	        "Accept": "application/json"
             	    }
         }).then(function mySuccess(response) {
         	
       	
         	$scope.Attachments=response.data;
         	
         
         }, function myError(response) {
           $scope.message = response.statusText;
         });
	};
	
	$scope.getuserDetails=function(userID)
	{
	     
	      
		 $http({
             url : '/v1/user/userDetails/'+ userID,
             method : "GET",
             	 headers: {
             	        "Content-Type": "application/json",
             	        "Accept": "application/json"
             	    }
         }).then(function mySuccess(response) {
         	
       	
         	$scope.UserInfo=response.data;
         	
         	$scope.cname=$scope.UserInfo[0].name;
         	$scope.cmail=$scope.UserInfo[0].emailID;
         	$scope.cmobile=$scope.UserInfo[0].mobile;
         }, function myError(response) {
           $scope.message = response.statusText;
         });
	};
	
	
    
  
    

    

    
    
    //---------------------
 //----
    
    $scope.getReqestID = function (index) {
        var reqID = $scope.Requests[index].requestID;
        
        $scope.requestID=reqID;
       /* $http({
            method : "DELETE",
            url : "/MyTaxReturn/v1/returnfilingrequest/request/" + reqID,
            data : angular.toJson(reqID),
           headers: {
              	        "Content-Type": "application/xml"
              	      
              	    }
        }).then(function mySuccess(response) {
          	
        	
          	//$scope.Requests=response.data;
        	_refreshFilingRequestData();
            $window.alert("RequestID: " +response.data);
            $window.location.reload();
          }, function myError(response) {
            $scope.message = response.statusText;
          });*/
        
      
    };
    
    //---------------------
    
    
    
} ]);