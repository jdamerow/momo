<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
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
                      {"bVisible": false},
                      {"bSortable": false},
                      {"bSortable": true}
                     ]
		});
	});
</script>
