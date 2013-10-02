<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#userTable').dataTable({
			"aaSorting" : [ [ 2, "asc" ], [1, "asc"] ],
			"aoColumns": [
                      {"bSortable": true},
                      {"iDataSort": 2},
                      {"bVisible": false},
                      {"bSortable": false},
                      {"bSortable": false},
                      {"iDataSort": 6},
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
					  {"bSortable": true},
					  {"iDataSort": 3},
	                  {"bVisible": false},
	                  {"iDataSort": 5},
	                  {"bVisible": false},
	                  {"bSortable": false},
	                  {"iDataSort": 8},
	                  {"bVisible": false}
	                 ]
		});
		$('#rejectedTable').dataTable({
			"aaSorting" : [ [ 2, "asc" ], [1, "asc"] ],
			"aoColumns": [
					  {"bSortable": false},
					  {"bSortable": true},
					  {"iDataSort": 3},
	                  {"bVisible": false},
	                  {"iDataSort": 5},
	                  {"bVisible": false},
	                  {"bSortable": false},
	                  {"iDataSort": 8},
	                  {"bVisible": false}
	                 ]
		});

		$('.splitList').easyListSplitter({
			colNumber : 3
		});
	});

	$(function() {
		$("#startdatepicker").datepicker();
	});
	$(function() {
		$("#enddatepicker").datepicker();
	});
</script>
