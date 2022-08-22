<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Search Results</title>
	</head>
	<body>
		<h1>Search Results</h1>
		<hr>
		
		<table>
			<tr>
				<th>Policyholder number</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Select</th>
			</tr>
			
			<c:forEach var="policyholder" items="${policyholderList}">	
			
				<c:url var="selectPolicyholder" value="/searchPolicyholders/showPolicyholderDetails">
					<c:param name="policyHolderNumber" value="${policyholder.policyHolderNumber}"/>
				</c:url>

				<tr>
					<td>${policyholder.policyHolderNumber}</td>
					<td>${policyholder.firstName}</td>
					<td>${policyholder.lastName}</td>
					<td>${policyholder.email}</td>
					<td>${policyholder.phoneNumber}</td>
					<td><a href="${selectPolicyholder}">Select</a></td>
				</tr>
			</c:forEach>
			
		</table>
		
	</body>

</html>