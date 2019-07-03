function doLogout() {
   $("#headerform").attr('action', 'logout');
  $("#headerform").attr('method', 'Get');
  $("#headerform").submit();
}