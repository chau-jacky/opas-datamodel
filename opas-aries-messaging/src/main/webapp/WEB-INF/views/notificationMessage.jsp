<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<table class="table table-striped table-sm">
	<thead>
	  <tr>
	    <th>Notification ID</th>
	    <th>Notification Message</th>
	  </tr>
	</thead>
	<tbody>
		<c:forEach items="${notificationMessage}" var="message">
			<tr>
			<td>${message.messageId}</td>
			<td>${message.messageDetails}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
