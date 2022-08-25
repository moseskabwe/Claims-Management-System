<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
	<head>
		<title>Simple Home Insurance Login Page</title>
		
		<style type="text/css">
			
			.login-error {
				color: red;
			}
			
			.logout-message {
				color: green;
			}
			
		</style>
		
	</head>
	<body>
		<h3>Please Login</h3>
		
		
		
		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			
			<c:if test="${param.error != null}">
		
				<i class="login-error">Invalid username/password.</i>
			
			</c:if>
			
			<c:if test="${param.logout != null}">
		
				<i class="logout-message">You have been logged out.</i>
			
			</c:if> 
			
			<p>Username: <input type="text" name="username"/></p>
			
			<p>Password: <input type="password" name="password"/></p>
			
			<input type="submit" value="Login"/>
		
		</form:form>
	</body>
</html>