<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

	<head>
	
		<title>Claims</title>	
	
	</head>

	<body>
	
		<h1>Claims</h1>
		<hr>
		
		<table>
			<tr>
				<th>Claim number</th>
				<th>Status</th>
				<th>Adjuster</th>
				<th>Report Date</th>
				<th>Policyholder Number</th>
				
			</tr>
			
			<c:forEach var="theClaim" items="${claimsList}">	
			
				<c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
					<c:param name="claimNumber" value="${theClaim.claimNumber}"/>
				</c:url>
				
				<c:url var="selectPolicyholder" value="/policyholders/showPolicyholderDetails">
					<c:param name="policyHolderNumber" value="${theClaim.policyHolder.policyHolderNumber}"/>
				</c:url>
				
				<tr>
					<td><a href="${showClaimDetails}">${theClaim.claimNumber}</a></td>
					<td>${theClaim.status}</td>
					<td>${theClaim.adjuster.firstName} ${theClaim.adjuster.lastName}</td>
					<td>${theClaim.filingDate}</td>
					<td><a href="${selectPolicyholder}">${theClaim.policyHolder.policyHolderNumber}</a></td>
					
					
				</tr>
			</c:forEach>
			
		</table>
	
	</body>

</html>

