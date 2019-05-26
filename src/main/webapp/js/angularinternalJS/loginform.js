var app = angular.module("signonApp", []);
app.controller("signonController", [ '$scope', '$http', function($scope, $http) {
 
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
     
    $scope.sendPost = function() {
    alert($scope.username);
        $http({
            url : '\MyLoginServlet',
            method : "POST",
            data : {
                'name' : $scope.username
            }
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