/**
 * 
 */
 
 function fnDisableEdit() {
  $('.table #editButton').attr('disabled','disabled');
}

$('document').ready(function() {
	
	//document.getElementById("editButton").disabled = true;
	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(grievance, status){
			$('#idDetailsEdit').val(grievance.id);
			$('#incidentdateDetailsEdit').val(grievance.incidentdate);
			$('#incidentlocationDetailsEdit').val(grievance.incidentlocation);
			$('#complaintdetailsDetailsEdit').val(grievance.complaintdetails);
			$('#resolvedbyDetailsEdit').val(grievance.resolvedby);
			$('#resolutiondetailsDetailsEdit').val(grievance.resolutiondetails);
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(grievance, status){
			$('#idDetails').val(grievance.id);
			$('#incidentdateDetails').val(grievance.incidentdate);
			$('#incidentlocationDetails').val(grievance.incidentlocation);
			$('#complaintdetailsDetails').val(grievance.complaintdetails);
			$('#resolvedbyDetails').val(grievance.resolvedby);
			$('#resolutiondateDetails').val(grievance.resolutiondate);
			$('#resolutiondetailsDetails').val(grievance.resolutiondetails);
		});			
		$('#detailsModal').modal();		
	});	
	
});