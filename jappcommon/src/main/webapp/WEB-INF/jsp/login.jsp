<html>
<head>
<title><fmt:message key="login.title" /></title>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
</head>
<body>
	<c:url var="_action" value="/login" />
	<form action="${_action }" method="post"
		class="form-horizontal form-signin">
		<c:if test="${not empty errorMessage}">
			<div class="control-group error">
				<span class="help-inline">${errorMessage }</span>
			</div>
		</c:if>
		<div class="control-group">
			<span class="add-on"><i class="icon-user"></i><input
				type="text" name="username" id="username"
				placeholder="<fmt:message key="login.username.label" />" required /></span>
		</div>
		<div class="control-group">
			<span class="add-on"><i class="icon-lock"></i></span><input
				type="password" name="password" id="password"
				placeholder="<fmt:message key="login.password.label" />" required />
		</div>
		<div class="control-group">
			<input type="checkbox" name="rememberMe" id="rememberMe"
				checked="checked" /><span class="label label-info"><fmt:message
					key="login.rememberMe.label" /></span>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="login.title" />
			</button>
		</div>
	</form>
</body>
</html>
