<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><fmt:message key="project.title" />-<decorator:title /></title>
<c:import url="/resources/common/head.jsp" />
<decorator:head />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<c:import url="/resources/common/userMenu.jsp" />
				<c:import url="/resources/common/menu.jsp" />
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<decorator:body />
	</div>
	<c:import url="/resources/common/foot.jsp" />
</body>
</html>