<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>My Claims</title>
	</head>
	<body>
		<h1>My Claims</h1>
		<hr>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout"/>
		</form:form>
		
		Logged in as ${user.firstName}, ${role}. <br>
		<a href=dashboard>Dashboard</a> <br>
		
		<h2>My Claims</h2>
		
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
			</tr>
			
			<c:forEach var="claim" items="${myClaims}">	
				
				<c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
					<c:param name="claimNumber" value="${claim.claimNumber}"/>
				</c:url>
				
				<tr>
					<td><a href="${showClaimDetails}">${claim.claimNumber}</a></td>
					<td>${claim.policyHolder.policyHolderNumber}</td>
					<td>${claim.policy.policyType}</td>
					<td>${claim.lossType}</td>
					<td>${claim.incidentDate}</td>
					<td>${claim.filingDate}</td>
					<td>${claim.adjuster.firstName} ${claim.adjuster.lastName}</td>
					<td>${claim.status}</td>								
				</tr>
			</c:forEach>
		</table>
		
	</body>
</html>
	
