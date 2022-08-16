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
				<th>Filing Date</th>
				<th>Status</th>
			</tr>
			
			<c:forEach var="theClaim" items="${claimsList}">			
				<tr>
					<td>${theClaim.claimNumber}</td>
					<td>${theClaim.filingDate}</td>
					<td>${theClaim.status}</td>
				</tr>
			</c:forEach>
			
		</table>
	
	</body>

</html>

