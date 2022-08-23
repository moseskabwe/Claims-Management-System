<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>
			Finalise Claim
		</title>		
	</head>
	<body>
		<h1>Finalise Claim Number ${claim.claimNumber}</h1>
		<hr>
		
		<form:form action="#" modelAttribute="payment" method="POST">
			<form:hidden path="paymentNumber"/>
			Payment Amount:
			<form:input type="text" path="paymentAmount"/>
			Notes:
			<form:input type="textarea" path="notes"/>
			<input type="submit" value="Submit"/> 
		</form:form>
	</body>
</html>