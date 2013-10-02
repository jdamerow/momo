<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#userTable').dataTable({
			"aaSorting" : [[1, "asc"] ],
			"aoColumns": [
                      {"iDataSort": 1},
                      {"bVisible": false},
                      {"bSortable": false},
                      {"bSortable": false},
                      {"iDataSort": 5},
                      {"bVisible": false},
                      {"bSortable": true},
                      {"bSortable": true},
                      {"bSortable": true}
                     ]
		});
		$('#requestTable').dataTable({
			"aaSorting" : [ [ 2, "asc" ], [1, "asc"] ],
			"aoColumns": [
					  {"bSortable": false},
					  {"iDataSort": 2},
	                  {"bVisible": false},
	                  {"iDataSort": 4},
	                  {"bVisible": false},
	                  {"bSortable": false},
	                  {"iDataSort": 7},
	                  {"bVisible": false}
	                 ]
		});
		$('#rejectedTable').dataTable({
			"aaSorting" : [ [ 2, "asc" ], [1, "asc"] ],
			"aoColumns": [
					  {"bSortable": false},
					  {"iDataSort": 2},
	                  {"bVisible": false},
	                  {"iDataSort": 4},
	                  {"bVisible": false},
	                  {"bSortable": false},
	                  {"iDataSort": 7},
	                  {"bVisible": false}
	                 ]
		});
	});

	$(function() {
		$("#startdatepicker").datepicker();
	});
	$(function() {
		$("#enddatepicker").datepicker();
	});

	
</script>
