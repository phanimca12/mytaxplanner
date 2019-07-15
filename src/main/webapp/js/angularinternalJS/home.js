var app = angular.module('homeApp', []);
app.controller('homeCTRL',  [ '$scope', '$http', '$window',function($scope, $http,$window) {
	
	$scope.Agents = [];
	$scope.Requests = [];
	$scope.Attachments = [];
	 $scope.Years = ["2015-2016", "2016-2017","2017-2018", "2018-2019","2019-2020"];
	 _refreshAgentData();
	
	$scope.getUser=function(customerName)
	{
	        
		 $http({
             url : '/MyTaxReturn/v1/returnfilingrequest/requestdetails/'+ customerName,
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
             url : '/MyTaxReturn/v1/attachments/download/'+ customerName,
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
              url : '/MyTaxReturn/v1/agent/agentdetails',
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