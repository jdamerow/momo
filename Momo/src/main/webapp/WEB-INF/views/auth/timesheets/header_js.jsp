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
