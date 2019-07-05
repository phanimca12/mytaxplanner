  $(document).ready(function() {
  function setHeight() {

    var top = $('#header').outerHeight();
    var bottom = $('#footer').outerHeight();
    var totHeight = $(window).height();
    $('#signinform').css({ 
      'height': totHeight - top - bottom + 'px'
       
    });
  }

  $(window).on('resize', function() { setHeight(); });
  setHeight();
});
