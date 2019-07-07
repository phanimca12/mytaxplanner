var app = angular.module("signonApp", []);
app.controller("signonController", [ '$scope', '$http','$location','$window', function($scope, $http,$location,$window) {
 
		
	
	$scope.submitForm=function(user)
	{
		
		 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		        
		
         $http({
             url : 'agentlogin',
             method : "POST",
             data : {
                 'name' : $scope.user.name,
                 'pass' :$scope.user.pass
             }
         }).then(function(response) {
        	 
        	 
        	 if(response.data=="Pass")
        		 {
        
        		 var host = $window.location.host;
      	        var landingUrl = "agenthome.jsp";
      	       $window.location.href = landingUrl;
        		 }
        	 else
        		 {
        		 alert("Invalid User/Password !");
        		 
        		 }
        	
             console.log(response.data);
             
         }, function(response) {
             //fail case
             console.log(response);
            
         });
		

    };
	
  
} ]);