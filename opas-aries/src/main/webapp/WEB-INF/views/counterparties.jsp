<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<table class="table table-striped table-sm">
	<thead>
	  <tr>
	    <th>Counterparty ID</th>
	    <th>Counterparty Acronym</th>
	    <th>Swift Address</th>
	    <th>Fax Number</th>
	  </tr>
	</thead>
	<tbody>
		<c:forEach items="${counterparties}" var="counterparty">
			<tr>
			<td>${counterparty.counterpartyId}</td>
			<td>${counterparty.counterpartyAcronym}</td>
			<td>${counterparty.swiftAddress}</td>
			<td>${counterparty.faxNumber}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
