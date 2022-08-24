<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

	<head>
	
		<title>Payments</title>	
	
	</head>

	<body>
	
		<h1>Claim Payment History</h1>
		<hr>
		
		<table>
			<tr>
				<th>Payment Number</th>
				<th>Claim Number</th>
				<th>Policyholder Number</th>
				<th>Amount</th>
				<th>Date</th>
				
			</tr>
			
			<c:forEach var="payment" items="${claimPayments}">	
			
				<c:url var="showClaimDetails" value="/dashboard/listClaims/showClaimDetails">
					<c:param name="claimNumber" value="${payment.claim.claimNumber}"/>
				</c:url>
				
				<c:url var="selectPolicyholder" value="/searchPolicyholders/showPolicyholderDetails">
					<c:param name="policyHolderNumber" value="${payment.claim.policyHolder.policyHolderNumber}"/>
				</c:url>
				
				<tr>
					<td>${payment.paymentNumber}</td>
					<td><a href="${showClaimDetails}">${payment.claim.claimNumber}</a></td>
					<td><a href="${selectPolicyholder}">${payment.claim.policyHolder.policyHolderNumber}</a></td>
					<td>${payment.paymentAmount}</td>
					<td>${payment.paymentDate}</td>
				</tr>
			</c:forEach>
			
		</table>
	
	</body>

</html>
