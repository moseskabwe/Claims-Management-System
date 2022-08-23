<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Decline Claim
		</title>		
	</head>
	<body>
		<h1>Decline Claim Number ${claim.claimNumber}</h1>
		<hr>
		
		<form:form action="#" modelAttribute="declinedClaim" method="POST">
			<form:hidden path="declinedClaimNumber"/>
			Reason:	
			<form:input type="textarea" path="reason"/>
			<input type="submit" value="Submit"/> 
		</form:form>
	</body>
</html>