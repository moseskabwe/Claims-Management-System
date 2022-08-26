<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Decline Claim
		</title>		
		
		<style>
			.error {
				color: red}
		</style>
		
	</head>
	<body>
		<h1>Decline Claim Number ${claim.claimNumber}</h1>
		<hr>
		
		<form:form action="#" modelAttribute="declinedClaim" method="POST">
			<form:hidden path="declinedClaimNumber"/>
			Reason:	
			<form:input type="textarea" path="reason"/>
			<form:errors path="reason" cssClass="error"></form:errors>			
			<input type="submit" value="Submit"/> 
		</form:form>
	</body>
</html>