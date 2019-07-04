var app = angular.module("signonApp", []);
app.controller("signonController", [ '$scope', '$http','$location','$window', function($scope, $http,$location,$window) {
 
	$scope.createUser=function(signup)
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
	             url : 'createuser',
	             method : "POST",
	             data : {
	                 'emailID' : $scope.signup.username,
	                 'mobile' :$scope.signup.mobile,
	                 'name':$scope.signup.name,
	                 'pass':$scope.signup.pass
	             }
	         }).then(function(response) {
	        	 
	        	 
	        	 if(response.data=="Pass")
	        		 {
	        		 alert("User Created Successfully !");
	        		 var host = $window.location.host;
	      	        var landingUrl = "login.html";
	      	       $window.location.href = landingUrl;
	        		 }
	        	 else
	        		 {
	        		 alert("User Already Exist !");
	        		 
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
	
	
	
	$scope.submitForm=function(user)
	{
		 $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
		        
		
         $http({
             url : 'login',
             method : "POST",
             data : {
                 'name' : $scope.user.name,
                 'pass' :$scope.user.pass
             }
         }).then(function(response) {
        	 
        	 
        	 if(response.data=="Pass")
        		 {
        
        		 var host = $window.location.host;
      	        var landingUrl = "home.jsp";
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