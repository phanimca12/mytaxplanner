
$(document).ready(function(){
	
	 $("#responseDiv").hide();
	$("#submitRequest").click(function(){
		
		// Get form
        var form = $('#ITR_Submit_Form')[0];

		// Create an FormData object 
        var formdata = new FormData(form);
        
		$.ajax({
			  method: "POST",
			  url: "returnfilingreq",
			  enctype: 'multipart/form-data',
		         
			  data: formdata,
			  processData: false,
	          contentType: false,
	           success: function (data) 
	           {
               alert(data);

	            },
			});
			
		
		
		 		}); 
	
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		localStorage.setItem('activeTab', $(e.target).attr('href'));
	});
	var activeTab = localStorage.getItem('activeTab');
	if(activeTab){
		$('#myTab a[href="' + activeTab + '"]').tab('show');
	}
});