<html>
<head>
<title><fmt:message key="corporation.manage.title" /></title>
</head>
<body>
	<form:form id="corporationUpdateForm" modelAttribute="command"
		method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<c:choose>
					<c:when test="${empty command.nodeId }">
						<fmt:message key="corporation.new.title" />
					</c:when>
					<c:otherwise>
						<fmt:message key="corporation.update.title" />
					</c:otherwise>
				</c:choose>
			</legend>

			<div class="control-group">
				<form:label for="uuid" path="uuid" class="control-label">
					UUID
				</form:label>
				<div class="controls">
					<form:input path="uuid" readonly="true" class="input-xlarge" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="shortName" path="shortName" class="control-label">
					<fmt:message key="shortname.label" />
				</form:label>
				<div class="controls">
					<form:input path="shortName" class="input-medium" required="true" />
					<form:errors path="shortName" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="fullName" path="fullName" class="control-label">
					<fmt:message key="fullname.label" />
				</form:label>
				<div class="controls">
					<form:input path="fullName" class="input-large" required="true" />
					<form:errors path="fullName" />
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<button id="corporationUpdateButton" type="button"
				class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var $corporationUpdateForm = $("#corporationUpdateForm");
			$("#corporationUpdateButton").on('click', function(event) {
				$corporationUpdateForm.submit();
			});
		});
	</script>
</body>
</html>