<shiro:notAuthenticated>
	<div class="btn-group pull-right">
		<a href="<c:url value="/login" />" class="btn"> <i
			class="icon-user"></i> <fmt:message key="login.title" />
		</a>
	</div>
</shiro:notAuthenticated>
<shiro:authenticated>
	<div class="btn-group pull-right">
		<a href="javascript:void(0)" class="btn btn-primary dropdown-toggle"
			data-toggle="dropdown"> <i class="icon-user icon-white"></i> <c:out
				value="${__SESSION_USER__.name}" /> <span class="caret"></span>
		</a>
		<ul class="dropdown-menu">
			<li><a href="#"><c:out value="${__SESSION_PRINCIPALTYPE__ }" />(<shiro:principal />)</a></li>
			<li class="divider"></li>
			<li><a href="<c:url value="/logout" />"><fmt:message
						key="logout.title" /></a></li>
		</ul>
	</div>
</shiro:authenticated>