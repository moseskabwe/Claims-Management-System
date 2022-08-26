<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>Dashboard</title>
	</head>
	<body>
		<h1>Dashboard</h1>
		<hr>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout"/>
		</form:form>
		Logged in as ${user.firstName}, ${role}. <br>
		<a href=dashboard>Dashboard</a> <br>
		
		<security:authorize access="hasRole('ADJUSTER')">
			<a href=dashboard/myClaims>My claims</a> <br>
		</security:authorize>
		
		<a href=dashboard/listClaims>Show all claims</a> <br>
		<a href=dashboard/showPayments>Show all payments</a> <br>
		<a href=dashboard/searchPolicyholders>Search for a policyholder</a> <br> <br>
		
		Number of fire claims: ${stats[0]} <br>
		Number of damage claims: ${stats[1]} <br>
		Number of theft claims: ${stats[2]} <br>
		Number of new claims: ${stats[3]} <br>
		Number of claims in progress: ${stats[4]} <br>
		Percentage of finalised claims: ${finalisedAverage}% <br>
		
		<security:authorize access="hasRole('ADJUSTER')">
		<h2>My Outstanding Claims</h2>
		
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
				
				<c:forEach var="myClaim" items="${myClaims}">	
					
					<c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
						<c:param name="claimNumber" value="${myClaim.claimNumber}"/>
					</c:url>
					
					<tr>
						<td><a href="${showClaimDetails}">${myClaim.claimNumber}</a></td>
						<td>${myClaim.policyHolder.policyHolderNumber}</td>
						<td>${myClaim.policy.policyType}</td>
						<td>${myClaim.lossType}</td>
						<td>${myClaim.incidentDate}</td>
						<td>${myClaim.filingDate}</td>
						<td>${myClaim.adjuster.firstName} ${myClaim.adjuster.lastName}</td>
						<td>${myClaim.status}</td>								
					</tr>
				</c:forEach>
			</table>
		</security:authorize>
		
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
			</tr>
			
			<c:forEach var="claim" items="${dashboardClaimsList}">	
				
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
	
