﻿var app = angular.module("signonApp", []);
app.controller("signonController", [ '$scope', '$http', function($scope, $http) {
 
	
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
     
    $scope.sendPost = function() {
    	var data = {

    			'name' :$scope.username,
                'pass' :$scope.userpass

    			};
    alert($scope.username);
        $http({
            url : '\MyLoginServlet',
            method : "POST",
            data :JSON.stringify(data)
        }).then(function(response) {
            console.log(response.data);
            $scope.message = response.data;
        }, function(response) {
            //fail case
            console.log(response);
            $scope.message = response;
        });
 
    };
} ]);