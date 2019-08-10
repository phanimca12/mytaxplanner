var app = angular.module('homeApp', []);
app.controller('homeCTRL',  [ '$scope', '$http', '$window',function($scope, $http,$window) {
	
	
	
	$scope.myVar = false;
	 
	$scope.Agents = [];
	$scope.Requests = [];
	$scope.Attachments = [];
	 $scope.Years = ["2015-2016", "2016-2017","2017-2018", "2018-2019","2019-2020"];
	 _refreshAgentData();
	
	 $scope.getAgentDetails=function(requestID)
		{
		   
			 $http({
	             url : '/v1/agent/agentData?paraName=requestID&paraValue='+ requestID,
	             method : "GET",
	             	 headers: {
	             	        "Content-Type": "application/json",
	             	        "Accept": "application/json"
	             	    }
	         }).then(function mySuccess(response) {
	         	
	       	
	         	$scope.UserInfo=response.data;
	         	
	         	$scope.aname=$scope.UserInfo[0].name;
	         	$scope.amail=$scope.UserInfo[0].emailID;
	         	$scope.amobile=$scope.UserInfo[0].mobile;
	         }, function myError(response) {
	           $scope.message = response.statusText;
	         });
		};
		
	 
		$scope.getAgentSelectedInfo=function(AgentCode)
		{
			
			if(AgentCode !="")
				{
						
		getAgentInfo(AgentCode)
		$scope.myVar = true;
		
		
				}
		
		};
		
	 
	$scope.getUser=function(customerName)
	{
	        
		 $http({
             url : '/v1/returnfilingrequest/requestdetails/'+ customerName,
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
	
	
	$scope.getAttachment=function(customerName)
	{
	        
		 $http({
             url : '/v1/attachments/download/'+ customerName,
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
	
 
	 /*_refreshFilingRequestData();*/
	
    /* Private Methods */
    //HTTP GET- get all countries collection
    function _refreshAgentData() {
    	  $http({
              url : '/v1/agent/agentdetails',
              method : "GET",
              	 headers: {
              	        "Content-Type": "application/json",
              	        "Accept": "application/json"
              	    }
          }).then(function mySuccess(response) {
          	
          	$scope.Agents=response.data;
          	
          
          }, function myError(response) {
            $scope.message = response.statusText;
          });
    }
    
    //----
    
    function getAgentInfo(AgentCode) {
  	  $http({
            url : '/v1/agent/agentData?paraName=AgentCode&paraValue='+ AgentCode,
            method : "GET",
            	 headers: {
            	        "Content-Type": "application/json",
            	        "Accept": "application/json"
            	    }
        }).then(function mySuccess(response) {
        	
     
        	$scope.AgentName=response.data[0].name;
        	$scope.AgentMobile=response.data[0].mobile;
        	$scope.AgentCity=response.data[0].city;
        
        }, function myError(response) {
          $scope.message = response.statusText;
        });
  }
    
    //----
    
    $scope.GetDetails = function (index) {
        var reqID = $scope.Requests[index].requestID;
        $http({
            method : "DELETE",
            url : "/v1/returnfilingrequest/request/" + reqID,
            data : angular.toJson(reqID),
           headers: {
              	        "Content-Type": "application/json",
              	        "Accept":"text/plain"
              	      
              	    }
        }).then(function(response) {
          	        	
            $window.alert(response.data);
            $window.location.reload();
          });
        
      
    };
    
    $scope.GetAttachDownload= function (index) {
    	
        var reqID = $scope.Attachments[index].requestID;
        $scope.attachlink.href="www.google.com";
        $http({
            method : "GET",
            url : "/MyTaxReturn/download",
            data : angular.toJson(reqID)
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