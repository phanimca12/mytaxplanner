function doLogout() {
  alert('hi logout');
  $("#headerform").attr('action', 'logout');
  $("#headerform").attr('method', 'Get');
  $("#headerform").submit();
}