<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Policies</title>
	</head>
	<body>
		<h1>Policy List for Policyholder ${policyholder.policyHolderNumber}</h1>
		<hr>
		
		Policyholder Number: ${policyholder.policyHolderNumber}<br>
		
		Name: ${policyholder.firstName} ${policyholder.lastName}<br>
		
		Email: ${policyholder.email}<br>
		
		Phone: ${policyholder.phoneNumber}<br>
		
		Address: ${policyholder.address}<br>
		

		
		<h2>Policies</h2>
		
		<table>
			<tr>
				<th>Policy number</th>
				<th>Policy type</th>
				<th>Property address</th>
				<th>Inception date</th>
				<th>Select</th>
			</tr>
		
			<c:forEach var="policy" items="${policyList}">
			
				<c:url var="selectPolicy" 
						value="/dashboard/addClaimDetails/${policyholder.policyHolderNumber}/${policy.policyNumber}">
					<c:param name="policyNumber" value="${policy.policyNumber}"/>
				</c:url>
			
				<tr>
					<td>${policy.policyNumber}</td>
					<td>${policy.policyType}</td>
					<td>${policy.property.propertyAddress}</td>
					<td>${policy.inceptionDate}</td>
					<td><a href="${selectPolicy}">Select</a></td>
				</tr>
			</c:forEach>	
		</table>
		

		
	</body>

</html>