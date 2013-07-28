<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>inner - <fmt:message key="error.title" /></title>
</head>
<body>
	<div>
		<h1>
			<fmt:message key="error.inner" />
		</h1>
	</div>
	<div>
		<a href="<c:url value="/"/>"><fmt:message key="returnhome" /></a>
	</div>
	<div>
		<c:out value="${exception}" />
		<c:out value="${exception.errorCode}" />
	</div>
</body>
</html>