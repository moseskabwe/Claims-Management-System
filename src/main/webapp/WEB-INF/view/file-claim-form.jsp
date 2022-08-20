<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
	<head>
		<title>Claim Form</title>
	</head>
	<body>
		<h1>Claim Form</h1>
		<hr>	
		
		<form:form action="saveClaim" modelAttribute="claim" method="POST">
			
<%-- 			<form:hidden path="claimNumber"/> --%>
			
			Policyholder Number: ${policyHolder.policyHolderNumber} <br>
			Policyholder Name: ${policyHolder.firstName} ${policyHolder.lastName}<br>

			Select the policy:
			<table>
				<c:forEach var="policy" items="${policies}">	
						
					<tr>
						<td>Policy: ${policy.policyNumber} | <form:radiobutton path="policy.policyNumber" value="${policy.policyNumber}"/>
						
						<form:hidden path="property.propertyNumber" value="${policy.property}" /> </td>
						
						<td>${policy.policyType} | </td>
						<td>${policy.property.propertyAddress} | </td>
						<td>${policy.inForce} | </td>
					</tr>
				</c:forEach>
			</table>
			<br>
			Customer Service Representative Number: <form:input path="customerServiceRep.customerServiceRepNumber"/>
			<br>
			Loss Type: 
			<form:select path="lossType">
				<form:option value="Damage">Damage</form:option>
				<form:option value="Fire">Fire</form:option>
				<form:option value="Theft">Theft</form:option>
			</form:select>
			<br>
			Incident Date:
			<form:input type="date" path="incidentDate"/>
			<br>
			Filing Date:
			<form:input type="date" path="filingDate"/>
			<br>
			Notes:
			<form:input type="textarea" path="notes"/> 
			<br>
			<input type="submit" value="Save Claim"/> 
		</form:form>