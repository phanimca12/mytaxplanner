
$(document).ready(function(){
	 $("#responseDiv").hide();
	$("#submitRequest").click(function(){
		  $("#responseDiv").show();
		 		}); 
	
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		localStorage.setItem('activeTab', $(e.target).attr('href'));
	});
	var activeTab = localStorage.getItem('activeTab');
	if(activeTab){
		$('#myTab a[href="' + activeTab + '"]').tab('show');
	}
});