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
                      {"bSortable": true}
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
