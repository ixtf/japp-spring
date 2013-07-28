<html>
<head>
<title><fmt:message key="corporation.manage.title" /></title>
</head>
<body>
	<table id="bindCorporationTable"
		class="table table-bordered table-hover">
		<thead>
			<tr>
				<th colspan="4"><fmt:message key="bindcorporation.label" /></th>
				<th colspan="3"><fmt:message key="corporation.label" /></th>
			</tr>
			<tr>
				<th><fmt:message key="type.label" /></th>
				<th><fmt:message key="bind.label" />ID</th>
				<th><fmt:message key="name.label" /></th>
				<th><fmt:message key="bind.label" /> <fmt:message
						key="operation.label" /></th>
				<th>UUID</th>
				<th><fmt:message key="shortname.label" />ID</th>
				<th><fmt:message key="fullname.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bindCorporations }" var="item" varStatus="status">
				<tr>
					<td>${item.bindCorporationType }</td>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td><div class="btn-group">
							<a
								href='<c:url value="/admin/bindCorporations/${item.nodeId }"  />'
								class="btn"><fmt:message key="edit.label" /></a> <a
								href='<c:url
							value="/admin/bindCorporationBind/${item.nodeId }" />'
								class="btn"><fmt:message key="bind.label" /></a>
							<button type="button" class="btn btn-danger">
								<fmt:message key="delete.label" />
							</button>
						</div></td>
					<c:choose>
						<c:when test="${not empty item.corporation }">
							<td>${item.corporation.uuid }</td>
							<td>${item.corporation.shortName }</td>
							<td>${item.corporation.fullName }</td>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		
	</script>
</body>
</html>