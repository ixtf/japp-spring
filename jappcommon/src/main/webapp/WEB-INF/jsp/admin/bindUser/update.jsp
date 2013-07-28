<html>
<head>
<title><fmt:message key="user.manage.title" /></title>
</head>
<body>
	<form:form id="bindUserUpdateForm" modelAttribute="command"
		method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<c:choose>
					<c:when test="${empty command.uuid }">
						<fmt:message key="user.new.title" />
					</c:when>
					<c:otherwise>
						<fmt:message key="user.update.title" />
					</c:otherwise>
				</c:choose>
			</legend>

			<div class="control-group">
				<form:label for="principalType" path="principalType"
					class="control-label">
					<fmt:message key="type.label" />
				</form:label>
				<div class="controls">
					<form:select path="principalType">
						<form:options items="${principalTypes }" />
					</form:select>
					<form:errors path="principalType" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="principal" path="principal" class="control-label">
					<fmt:message key="principal.label" />
				</form:label>
				<div class="controls">
					<form:input path="principal" class="input-medium" required="true" />
					<form:errors path="principal" />
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
			<button id="bindUserUpdateButton" type="button"
				class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var $bindUserUpdateForm = $("#bindUserUpdateForm");
			$("#bindUserUpdateButton").on('click', function(event) {
				$bindUserUpdateForm.submit();
			});
		});
	</script>
</body>
</html>