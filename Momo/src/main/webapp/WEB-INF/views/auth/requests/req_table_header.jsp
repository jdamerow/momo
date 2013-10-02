<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
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
                      {"bVisible": false},
                      {"bSortable": false},
                      {"bSortable": true}
                     ]
		});
	});
</script>
