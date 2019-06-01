var app=angular.module("myhomeapp",[]);
app.controller("myhomectrl", function($scope, $http,$window)
{
$window.onload = function()
{

$http({
    method : "GET",
      url : "/MyTaxReturn/v1/HTTPSession"
  }).then(function mySuccess(response) {
    $scope.username = response.data;
  }, function myError(response) {
    $scope.username = response.statusText;
  }); 
        
};   
            });