<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="container">
	<div class="row">
		<c:forEach items="${counterparties}" var="counterparty">
			<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<div class="caption">
						<h3>${counterparty.name}</h3>
						<p>${counterparty.description}</p>
						<p>$${counterparty.unitPrice}</p>
						<p>Available ${counterparty.unitsInStock} units in stock</p>
						<p>
							<a
								href=" <spring:url value="/opas/counterparty?id=${counterparty.counterpartyId}" /> "
								class="btn btn-primary"> <span
								class="glyphicon-info-sign glyphicon" /></span> Details
							</a>
						</p>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
