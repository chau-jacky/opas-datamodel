<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post" class="form-signin">
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">OPAS-Aries</h1>
			<p>
				Unauthorised access is strictly forbidden and may result in the unauthorised user being prosecuted under the law.
			</p>
		</div>

		<div class="form-label-group">
			<input type="email" id="userId" name="userId" class="form-control" placeholder="Email address" required autofocus>
			<label for="inputEmail">Email address</label>
		</div>

		<div class="form-label-group">
			<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
			<label for="inputPassword">Password</label>
		</div>

		<div class="checkbox mb-3">
			<label>
				<input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit" value="Log in">Sign in</button>

		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
				<p>Invalid username and password.</p>
			</div>
		</c:if>

		<c:if test="${param.logout != null}">
			<div class="alert alert-success">
				<p>You have been logged out successfully.</p>
			</div>
		</c:if>

		<c:if test="${param.accessDenied != null}">
			<div class="alert alert-danger">
				<p>Access Denied: You are not authorised!</p>
			</div>
		</c:if>		
		
		<p class="mt-5 mb-3 text-muted text-center">&copy; Copyright. xxxx Holdings Plc 2018. All rights reserved.</p>
	</form>
