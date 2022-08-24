<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>
			Claim Details
			
			<c:url var="finaliseClaim" value="/dashboard/finaliseClaim">
				<c:param name="claimNumber" value="${claim.claimNumber}"/>
			</c:url>
			
		</title>		
	</head>
	<body>
		<h1>Claim Number ${claim.claimNumber}</h1>
		<hr>
		Status: ${claim.status}<br>
		Loss Type: ${claim.lossType}<br>
		Incident Date: ${claim.incidentDate}<br>
		Date reported: ${claim.filingDate}<br>
		
		<c:if test = "${claim.notes != null}">
			Additional Details: ${claim.notes}<br>
		</c:if>
		
		<h2>Policyholder Details</h2>
		Policyholder Number: ${claim.policyHolder.policyHolderNumber}<br>
		Name: ${claim.policyHolder.firstName} ${claim.policyHolder.lastName}<br>
		Email: ${claim.policyHolder.email}<br>
		Phone: ${claim.policyHolder.phoneNumber}<br>
		Address: ${claim.policyHolder.address}<br>
		
		<h2>Policy Details</h2>
		Policy Number: ${claim.policy.policyNumber}<br>
		PolicyType: ${claim.policy.policyType}<br>
		Property Address: ${claim.policy.property.propertyAddress}<br>
		Inception Date: ${claim.policy.inceptionDate}<br>
		
		<c:if test = "${claim.policy.inForce == 1}">
			Policy in force<br>
		</c:if>
		
		<c:if test = "${claim.policy.inForce == 0}">
			Policy not in force<br>
			End Date: ${claim.policy.endDate}<br>
		</c:if>
		
		<c:if test = "${claim.status == 'Finalised'}">
			<h2>Decision</h2>
			Claim Approved<br>
			<h3>Payment Details</h3>
			
			<table>
				<tr>
					<th>Date</th>
					<th>Amount</th>
					<th>Notes</th>
				</tr>
				<c:forEach var="payment" items="${paymentsList}">	
					<tr>
						<td>${payment.paymentDate}</td>
						<td>${payment.paymentAmount}</td>
						<td>${payment.notes}</td>
					</tr>
				</c:forEach>				
			</table>
			
			
			
			<a href="${finaliseClaim}">Add a payment</a>			
		</c:if>
		
		<c:if test = "${claim.status == 'Declined'}">
			<h2>Decision</h2>
			Claim Declined<br>
			Date: ${declinedClaim.declinedDate}<br>
			Reason: ${declinedClaim.reason}		
		</c:if>
		
		<c:if test = "${claim.status == 'In Progress'}">
		
			<c:url var="declineClaim" value="/declineClaim">
				<c:param name="claimNumber" value="${claim.claimNumber}"/>
			</c:url>
					
			<h2>Decision</h2>
			<a href="${finaliseClaim}">Finalise Claim</a> | <a href="${declineClaim}">Decline Claim</a>			
		</c:if>
		
</html>
	
