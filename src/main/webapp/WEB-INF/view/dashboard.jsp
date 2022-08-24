<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Dashboard</title>
	</head>
	<body>
		<h1>Dashboard</h1>
		<hr>
		<a href=dashboard>Dashboard</a> <br>
		<a href=dashboard/listClaims>Show all claims</a> <br>
		<a href=dashboard/fileClaim>File a claim</a> <br>
		<a href=dashboard/showPayments>Show all payments</a> <br>
		<a href=dashboard/fileClaim>Search for a policyholder</a> <br>
		
		<h2>Outstanding Claims</h2>
		
		<table>
			<tr>
				<th>Claim Number</th>
				<th>Policyholder Number</th>
				<th>Policy Type</th>
				<th>Loss Type</th>
				<th>Incident Date</th>
				<th>Report Date</th>
				<th>Adjuster</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="claim" items="${dashboardClaimsList}">	
			
				<c:url var="maintainClaim" value="dashboard/listClaims/showClaimDetails">
					<c:param name="claimNumber" value="${claim.claimNumber}"/>
				</c:url>
				
				<tr>
					<td>${claim.claimNumber}</td>
					<td>${claim.policyHolder.policyHolderNumber}</td>
					<td>${claim.policy.policyType}</td>
					<td>${claim.lossType}</td>
					<td>${claim.incidentDate}</td>
					<td>${claim.filingDate}</td>
					<td>${claim.adjuster.firstName} ${claim.adjuster.lastName}</td>
					<td>${claim.status}</td>
					<td><a href="${maintainClaim}">Maintain</a></td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
</html>
	
