<html>
<head>
<title><fmt:message key="user.manage.title" /></title>
</head>
<body>
	<form:form id="userUpdateForm" modelAttribute="command" method="post"
		class="form-horizontal">
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
				<form:label for="uuid" path="uuid" class="control-label">
					UUID
				</form:label>
				<div class="controls">
					<form:input path="uuid" class="input-large" readonly="true" />
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
			<button id="userUpdateButton" type="button" class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<script type="text/javascript">
		$(function() {
			var $userUpdateForm = $("#userUpdateForm");
			$("#userUpdateButton").on('click', function(event) {
				$userUpdateForm.submit();
			});
			$("#zvbelnInput").on('keypress', function(event) {
				if (event.keyCode == 13)
					addZvbeln(this);
			}).on('blur', function(event) {
				this.value = '';
			});
		});
	</script>
</body>
</html>