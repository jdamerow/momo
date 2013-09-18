<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#userTable').dataTable({
			"aaSorting" : [ [ 2, "desc" ], [1, "desc"] ]
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
