
$(document).ready(function(){
	
$("#modifyRequest").click(function()
		{
	var selected_value = $("#mfileyear").val();
	
	if(!selected_value.match("undefined") )
		{
		alert("Hii");
		// Get form
        var form = $('#modifyITRForm')[0];

		// Create an FormData object 
        var formdata = new FormData(form);
        
		$.ajax({
			  method: "POST",
			  url: "modifyITR",
			  enctype: 'multipart/form-data',
		         
			  data: formdata,
			  processData: false,
	          contentType: false,
	           success: function (data) 
	           {
               alert(data);

	            }
			});
			
		}
	else
		{
		alert("Please Select Assesment Year");
		
		
		}
		
	});
	
	//-----------
	
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
               location.reload();
	            }
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