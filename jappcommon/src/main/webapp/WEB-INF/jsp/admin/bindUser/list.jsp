<html>
<head>
<title><fmt:message key="user.manage.title" /></title>
</head>
<body>
	<c:url value='/admin/userSearch' var="userSearchAction" />
	<form:form id="userSearchForm" action="${userSearchAction }"
		modelAttribute="command" method="post" class="form-search">
		<div class="input-append">
			<form:input path="name" class="input-medium search-query"
				required="true" />
			<button id="userSearchButton" type="button" class="btn">
				<fmt:message key="search.label" />
			</button>
		</div>
	</form:form>
	<table id="userTable" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th rowspan="2">ID</th>
				<th rowspan="2"><fmt:message key="name.label" /></th>
				<th rowspan="2"><fmt:message key="operation.label" /></th>
				<th colspan="4"><fmt:message key="bind.label" /></th>
			</tr>
			<tr>
				<th><fmt:message key="type.label" /></th>
				<th><fmt:message key="principal.label" /></th>
				<th><fmt:message key="name.label" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users }" var="item" varStatus="status">
				<c:set value="${item.bindUserSize }" var="rowspan" />
				<c:if test="${item.bindUserSize <= 1 }">
					<c:set value="1" var="rowspan" />
				</c:if>
				<tr id="${item.uuid }">
					<td rowspan="${rowspan }">${item.uuid }</td>
					<td rowspan="${rowspan }">${item.name }</td>
					<td rowspan="${rowspan }">
						<div class="btn-group">
							<a href='<c:url
							value="/admin/users/${item.uuid }" />'
								class="btn"><fmt:message key="edit.label" /></a><a
								href='<c:url
							value="/admin/userBind/${item.uuid }" />'
								class="btn"><fmt:message key="bind.label" /></a>
						</div>
					</td>
					<c:choose>
						<c:when test="${not empty item.bindUsers }">
							<c:forEach items="${item.bindUsers }" var="bindUser"
								varStatus="status">
								<c:if test="${status.first }">
									<td>${bindUser.principalType.name }</td>
									<td>${bindUser.principal }</td>
									<td>${bindUser.name }</td>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:forEach items="${item.bindUsers }" var="bindUser"
					varStatus="status">
					<c:if test="${!status.first }">
						<tr>
							<td>${bindUser.principalType.name }</td>
							<td>${bindUser.principal }</td>
							<td>${bindUser.name }</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${not empty pageNumber }">
		<c:url value="/admin/users/page/" var="pageUrl" />
		<div class="pagination pull-right">
			<ul>
				<c:choose>
					<c:when test="${pageNumber < 1 }">
						<li class="disabled"><span>&laquo;</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl }${pageNumber-1 }">&laquo;</a></li>
					</c:otherwise>
				</c:choose>
				<li class="active"><span>${pageNumber+1 }</span></li>
				<c:choose>
					<c:when test="${empty users }">
						<li class="disabled"><span>&raquo;</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl }${pageNumber+1 }">&raquo;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</c:if>
	<script type="text/javascript">
		$(function() {
			$("#userSearchButton").on('click', function() {
				$form = $("#userSearchForm");
				$form.submit();
			});
			$('#userUpdateFormModal').on('show', function() {
			});
		});
	</script>

</body>
</html>