<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Search for Policyholders</title>
	</head>
	<body>
		<h1>File a claim</h1>
		<hr>
		<h2>Search for a policyholder</h2>
		<form:form modelAttribute="policyHolder" action="searchPolicyholders" method="GET">
			Search policyholder: <input type="text" name="searchTerm"/>
			<input type="submit" value="Search">
		</form:form>
		
	</body>

</html>