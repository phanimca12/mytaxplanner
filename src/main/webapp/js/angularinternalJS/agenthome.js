var app = angular.module('agenthomeApp', []);

app.controller('agenthomeCTRL',  [ '$scope', '$http', '$window',function($scope, $http,$window) {
	
	
	$scope.Agents = [];
	$scope.Requests = [];
	$scope.Attachments = [];
	$scope.status = ["pending", "inprogress","complete", "All"];
	$scope.mystatus = ["pending", "inprogress","complete"];
	 $scope.Years = ["2015-2016", "2016-2017","2017-2018", "2018-2019"];
	 
	
	 
	 $scope.getSelectedValue=function()
	 {
		 alert($scope.selectedStatus);
		 
	 };
	$scope.getAllRequest=function(AgentName)
	{
	       //alert(AgentName +"status="+$scope.selectedStatus);
		 $http({
             url : '/MyTaxReturn/v1/returnfilingrequest/requestdetails/'+ AgentName+'/'+$scope.selectedStatus,
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