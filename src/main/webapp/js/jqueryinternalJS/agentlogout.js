function doLogout() {
   $("#headerform").attr('action', 'agentlogout');
  $("#headerform").attr('method', 'Get');
  $("#headerform").submit();
}