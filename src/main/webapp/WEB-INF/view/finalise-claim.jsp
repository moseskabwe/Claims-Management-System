<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>
			Finalise Claim
		</title>
		<style>
			.error {
				color: red}
		</style>		
	</head>
	<body>
		<c:if test="${claim.status == 'In Progress'}">
			<h1>Finalise Claim Number ${claim.claimNumber}</h1>
		</c:if>
		
		<c:if test="${claim.status == 'Finalised'}">
			<h1>Add Payment to Claim Number ${claim.claimNumber}</h1>
		</c:if>
		
		<hr>
		
		<form:form action="#" modelAttribute="payment" method="POST">
			<form:hidden path="paymentNumber"/>
			Payment Amount:
			<form:input type="text" path="paymentAmount"/>
			<form:errors path="paymentAmount" cssClass="error"></form:errors>	
			Notes:
			<form:input type="textarea" path="notes"/>
			<input type="submit" value="Submit"/> 
		</form:form>
	</body>
</html>