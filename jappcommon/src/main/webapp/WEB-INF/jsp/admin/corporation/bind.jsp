<html>
<head>
<title><fmt:message key="corporation.manage.title" /></title>
</head>
<body>
	<form:form id="bindCorporationForm" modelAttribute="command"
		method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<fmt:message key="corporation.bind.title" />
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
				<form:label for="id" path="id" class="control-label typeahead">
					<fmt:message key="bind.label" />ID
				</form:label>
				<div class="controls">
					<form:input path="id" required="true" />
					<form:errors path="id" />
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
			<button id="bindCorporationButton" type="button"
				class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="operation.submit" />
			</button>
		</div>
	</form:form>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th><fmt:message key="type.label" /></th>
				<th><fmt:message key="bind.label" />ID</th>
				<th><fmt:message key="name.label" /></th>
				<th><fmt:message key="operation.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${corporation.bindCorporations }" var="item"
				varStatus="status">
				<tr>
					<td>${item.bindCorporationType }</td>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td><button type="button" class="btn">
							<fmt:message key="delete.label" />
						</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		$(function() {
			var $bindCorporationForm = $("#bindCorporationForm");

			$("#bindCorporationButton").on('click', function(event) {
				$bindCorporationForm.submit();
			});

			$bindCorporationForm
					.find("input[name='id']")
					.typeahead(
							{
								name : "bindCorporation",
								template : '<p>{{name}}</p>',
								engine : Hogan,
								remote : {
									url : '<c:url value="/ws/rest/typeahead/bindCorporation/" />',
									replace : function(url, uriEncodedQuery) {
										return url
												+ $("#bindCorporationType")
														.val() + "/"
												+ uriEncodedQuery;
									}
								}
							});
		});
	</script>
</body>
</html>