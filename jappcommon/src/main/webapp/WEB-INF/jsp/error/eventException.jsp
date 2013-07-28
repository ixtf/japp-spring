<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>eventException - <fmt:message key="error.title" /></title>
</head>

<body>
	<c:set value="${exception.cause }" var="exception" />
	<div class="alert alert-block alert-error fade in">
		<h4 class="alert-heading">
			<fmt:message key="${exception.errorCode }">
				<fmt:param value="${exception.params }" />
			</fmt:message>
		</h4>
	</div>
	<div>
		<a href="<c:url value="/"/>"><fmt:message key="returnhome" /></a>
	</div>
</body>
</html>