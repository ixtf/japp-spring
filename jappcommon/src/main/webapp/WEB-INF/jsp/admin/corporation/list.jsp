<html>
<head>
<title><fmt:message key="corporation.manage.title" /></title>
</head>
<body>
	<table id="corporationTable" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th rowspan="2">ID</th>
				<th rowspan="2"><fmt:message key="shortname.label" /></th>
				<th rowspan="2"><fmt:message key="fullname.label" /></th>
				<th rowspan="2"><fmt:message key="operation.label" /></th>
				<th colspan="4"><fmt:message key="bind.label" /></th>
			</tr>
			<tr>
				<th><fmt:message key="type.label" /></th>
				<th><fmt:message key="bind.label" />ID</th>
				<th><fmt:message key="name.label" /></th>
				<th><fmt:message key="bind.label" /> <fmt:message
						key="operation.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${corporations }" var="item" varStatus="status">
				<c:set value="${item.bindCorporationSize }" var="rowspan" />
				<c:if test="${rowspan < 1 }">
					<c:set value="1" var="rowspan" />
				</c:if>
				<tr id="${item.uuid }">
					<td rowspan="${rowspan }">${item.uuid }</td>
					<td rowspan="${rowspan }">${item.shortName }</td>
					<td rowspan="${rowspan }">${item.fullName }</td>
					<td rowspan="${rowspan }"><div class="btn-group">
							<a href='<c:url value="/admin/corporations/${item.uuid }"  />'
								class="btn"><fmt:message key="edit.label" /></a> <a
								href='<c:url
							value="/admin/corporationBind/${item.uuid }" />'
								class="btn"><fmt:message key="bind.label" /></a>
							<button type="button" class="btn btn-danger">
								<fmt:message key="delete.label" />
							</button>
						</div></td>
					<c:choose>
						<c:when test="${not empty item.bindCorporations }">
							<c:forEach items="${item.bindCorporations }"
								var="bindCorporation" varStatus="status">
								<c:if test="${status.first }">
									<td>${bindCorporation.bindCorporationType }</td>
									<td>${bindCorporation.id }</td>
									<td>${bindCorporation.name }</td>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
					<td></td>
				</tr>
				<c:forEach items="${item.bindCorporations }" var="bindCorporation"
					varStatus="status">
					<c:if test="${!status.first }">
						<tr>
							<td>${bindCorporation.bindCorporationType }</td>
							<td>${bindCorporation.id }</td>
							<td>${bindCorporation.name }</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		
	</script>
</body>
</html>