<div id="ajaxLoading" class="ui-widget-overlay ui-widget-shadow"
	style="display: none">
	<img src='<c:url value="/resources/images/ajax-loader.gif" />' />
</div>
<script type="text/javascript">
	function ajaxLoading() {
		$(".container-fluid").hide();
		$("#ajaxLoading").show();
	}
	$(function() {
		$("form").validate({
			errorClass : 'help-inline',
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
				element.parents('div .control-group').addClass('error');
			},
			success : function(label) {
				label.parents('div .control-group').removeClass('error');
			}
		});
		$("#ajaxLoading").bind("ajaxSend", function() {
			$(".container-fluid").hide();
			$(this).show();
		}).bind("ajaxComplete", function() {
			$(".container-fluid").show();
			$(this).hide();
		});
		/* $.datepicker.setDefaults($.datepicker.regional["zh-CN"]); */
		$.extend($.fn.dataTable.defaults, {
			"bProcessing" : true,
			"bStateSave" : true,
			"bScrollInfinite" : true
		});
	});
</script>