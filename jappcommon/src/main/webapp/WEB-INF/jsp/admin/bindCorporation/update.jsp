<html>
<head>
<title><fmt:message key="corporation.manage.title" /></title>
</head>
<body>
	<form:form id="bindCorporationUpdateForm" modelAttribute="command"
		method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<c:choose>
					<c:when test="${empty command.nodeId }">
						<fmt:message key="bindcorporation.new.title" />
					</c:when>
					<c:otherwise>
						<fmt:message key="bindcorporation.update.title" />
					</c:otherwise>
				</c:choose>
			</legend>

			<div class="control-group">
				<form:label for="bindCorporationType" path="bindCorporationType"
					class="control-label">
					<fmt:message key="type.label" />
				</form:label>
				<div class="controls">
					<form:select path="bindCorporationType">
						<form:options items="${corporationTypes }" />
					</form:select>
					<form:errors path="bindCorporationType" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="id" path="id" class="control-label">
					ID
				</form:label>
				<div class="controls">
					<form:input path="id" class="input-medium" required="true" />
					<form:errors path="id" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="name" path="name" class="control-label">
					<fmt:message key="name.label" />
				</form:label>
				<div class="controls">
					<form:input path="name" class="input-medium" required="true" />
					<form:errors path="name" />
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<button id="bindCorporationUpdateButton" type="button"
				class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var $bindCorporationUpdateForm = $("#bindCorporationUpdateForm");
			$("#bindCorporationUpdateButton").on('click', function(event) {
				$bindCorporationUpdateForm.submit();
			});
		});
	</script>
</body>
</html>