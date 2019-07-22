
$(document).ready(function(){
	
	$(".Section_2").hide();
	$(".Section_3").hide();
	$(".Section_4").hide();
	$(".Section_5").hide();
	$(".Section_6").hide();

	$("#itr_section").click(function()
	{		$(".Section_2").show();
		$(".Section_3").hide();
		$(".Section_4").hide();
		$(".Section_5").hide();
		$(".Section_6").hide();
		$(".Section_1").hide();
	}
	
	);

	$("#home_section").click(function()
					{
						$(".Section_1").show();
						$(".Section_3").hide();
						$(".Section_4").hide();
						$(".Section_5").hide();
						$(".Section_6").hide();
						$(".Section_2").hide();
					}
					
					);
	

	$("#download_section").click(function()
					{
						$(".Section_3").show();
						$(".Section_2").hide();
						$(".Section_4").hide();
						$(".Section_5").hide();
						$(".Section_6").hide();
						$(".Section_1").hide();
					}
					
					);
	
	$("#it_details").click(function()
					{
						$(".Section_4").show();
						$(".Section_2").hide();
						$(".Section_3").hide();
						$(".Section_5").hide();
						$(".Section_6").hide();
						$(".Section_1").hide();
					}
					
					);
	$("#contact_section").click(function()
					{
						$(".Section_5").show();
						$(".Section_2").hide();
						$(".Section_3").hide();
						$(".Section_4").hide();
						$(".Section_6").hide();
						$(".Section_1").hide();
					}
					
					);
	$("#pricing_section").click(function()
					{
						$(".Section_6").show();
						$(".Section_2").hide();
						$(".Section_3").hide();
						$(".Section_4").hide();
						$(".Section_5").hide();
						$(".Section_1").hide();
					}
					
					);
$("#modifyRequest").click(function()
		{
	var selected_value = $("#mfileyear").val();
	
	if(!selected_value.match("undefined") )
		{
		
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
               location.reload();
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