var app = angular.module("signonApp", []);
app.controller("signonController", [ '$scope', '$http','$location','$window', function($scope, $http,$location,$window) {
 
	$scope.createAgent=function(signup)
	{
		if($scope.signup.username !="" && $scope.signup.mobile !="" && $scope.signup.name && $scope.signup.pass !=""  && $scope.signup.cpass  )
		{
			if($scope.signup.pass != $scope.signup.cpass)
			{
		alert("Password and Confirm Passowrd Not Matching !");
			}
		else
			{
			
			 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		    
				
	         $http({
	             url : 'createagent',
	             method : "POST",
	             data : {
	                 'aemail' : $scope.signup.username,
	                 'amobile' :$scope.signup.mobile,
	                 'aname':$scope.signup.name,
	                 'apassword':$scope.signup.pass
	             }
	         }).then(function(response) {
	        	 
	        	 
	        	 if(response.data=="Pass")
	        		 {
	        		 alert("Agent Created Successfully !");
	        		 var host = $window.location.host;
	      	        var landingUrl = "agentlogin.html";
	      	       $window.location.href = landingUrl;
	        		 }
	        	 else
	        		 {
	        		 alert("Agent Already Exist !");
	        		 
	        		 }
	        	
	             console.log(response.data);
	             
	         }, function(response) {
	             //fail case
	             console.log(response);
	            
	         });
			}
		}
		else
			{
			alert("Please Fill All the Fields !");
			
			}
		
	};
	
	$scope.submitAgentForm=function(agent)
	{
		alert("Hii");
		
		 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		        
		
         $http({
             url : 'agentlogin',
             method : "POST",
             data : {
                 'name' : $scope.agent.aname,
                 'pass' :$scope.agent.apass
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