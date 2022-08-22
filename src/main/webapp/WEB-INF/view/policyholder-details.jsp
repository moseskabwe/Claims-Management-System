<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Policyholder Details</title>
	</head>
	<body>
		<h1>Policyholder Details</h1>
		<hr>
		
		Policyholder Number: ${policyholder.policyHolderNumber}<br>
		
		Name: ${policyholder.firstName} ${policyholder.lastName}<br>
		
		Email: ${policyholder.email}<br>
		
		Phone: ${policyholder.phoneNumber}<br>
		
		Address: ${policyholder.address}<br>
		
		<c:url var="showPolicyDetails" value="/searchPolicyholders/showPolicyDetails/${policyholder.policyHolderNumber}">
			<c:param name="policyHolderNumber" value="${policyholder.policyHolderNumber}"/>
		</c:url>
		
		<a href="${showPolicyDetails}">File a claim</a>
		
		<h2>Claims History</h2>
		
		<table>
			<tr>
				<th>Claim number</th>
				<th>Status</th>
				<th>Loss Type</th>
				<th>Incident Date</th>
				<th>Report Date</th>
			</tr>
		
			<c:forEach var="claim" items="${claimList}">
			
				<c:url var="showClaimDetails" value="/listClaims/showClaimDetails">
					<c:param name="claimNumber" value="${claim.claimNumber}"/>
				</c:url>
			
				<tr>
					<td><a href="${showClaimDetails}">${claim.claimNumber}</a></td>
					<td>${claim.status}</td>
					<td>${claim.lossType}</td>
					<td>${claim.incidentDate}</td>					
					<td>${claim.filingDate}</td>
				</tr>
			</c:forEach>	
		</table>.
		
	</body>

</html>