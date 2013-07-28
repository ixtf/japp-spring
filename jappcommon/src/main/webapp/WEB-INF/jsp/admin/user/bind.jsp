<html>
<head>
<title><fmt:message key="user.manage.title" /></title>
</head>
<body>
	<form:form id="bindUserForm" modelAttribute="command" method="post"
		class="form-horizontal">
		<fieldset>
			<legend>
				<fmt:message key="user.bind.title" />
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
					<form:input path="principal" required="true" />
					<form:errors path="principal" />
				</div>
			</div>

			<div class="control-group">
				<form:label for="name" path="name" class="control-label">
					<fmt:message key="name.label" />
				</form:label>
				<div class="controls">
					<form:input path="name" />
					<form:errors path="name" />
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<button id="bindUserButton" type="button" class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th><fmt:message key="type.label" /></th>
				<th><fmt:message key="principal.label" /></th>
				<th><fmt:message key="name.label" /></th>
				<th><fmt:message key="operation.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${user.bindUsers }" var="item" varStatus="status">
				<tr>
					<td>${item.principalType.name }</td>
					<td>${item.principal }</td>
					<td>${item.name }</td>
					<td><a class="btn"
						href='<c:url
							value="/admin/users/" />'><fmt:message
								key="delete.label" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		$(function() {
			var $bindUserForm = $("#bindUserForm");

			$("#bindUserButton").on('click', function(event) {
				$bindUserForm.submit();
			});
		});
	</script>
</body>
</html>