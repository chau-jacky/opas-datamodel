<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><tiles:insertAttribute name="title" /></title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="/opas-aries/resources/dashboard.css" rel="stylesheet">
<link href="/opas-aries/resources/typeahead.css" rel="stylesheet">
<!-- 
<link href="floating-labels.css" rel="stylesheet">
 -->


<!-- 
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="http://bootstrap.hexschool.com/assets/js/vendor/jquery-slim.min.js"></script>
<script src="/webstore/resources/js/controllers.js"></script>
-->

</head>

<body>

<!-- 
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/opas-aries">OPAS-Aries</a>
		<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search" id="searchInput">

		<ul class="navbar-nav px-3">
			<li class="nav-item dropdown">
				<a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle" aria-haspopup="true" aria-expanded="false">
					<span data-feather="user"></span>
					Username
				</a>
				<div class="dropdown-menu" aria-lablledby="userdropdown">
					<a class="dropdown-item" href="#">Action 01</a>
					<a class="dropdown-item" href="#">Action 02</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Action 03</a>
				</div>
			</li>
		</ul>
	</nav>
 -->
 
    <nav class="navbar navbar-expand-md navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/opas-aries">
      	OPAS-Aries
      	<span class="badge badge-pill badge-danger">DEMO</span>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample04">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/opas-aries">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">Disabled</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            	<span data-feather="user"></span>
            	Username
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown04">
              <a class="dropdown-item" href="#">
              	<span data-feather="settings"></span>
              	User Settings
		      </a>
              <a class="dropdown-item" href="#">
              	<span data-feather="mail"></span>
              	Notifications
              	<span class="badge badge-pill badge-danger">99</span>
		      </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="/opas-aries/logout">
              	<span data-feather="log-out"></span>
              	Logout
              </a>
            </div>
          </li>
        </ul>
        <form class="form-inline">
          <input class="form-control typeahead tt-query" type="text" placeholder="Go to menu..." id="userSearch" autocomplete="off" spellcheck="false">
        </form>
      </div>
    </nav>


    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="/opas-aries/opas/dashboard">
                  <span data-feather="home"></span>
                  Dashboard
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="inbox"></span>
                  Pending for Actions
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="activity"></span>
                  Activity
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="alert-triangle"></span>
                  Trade Exception
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="book-open"></span>
                  Trade Enquiry
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/opas-aries/opas/counterparties">
                  <span data-feather="search"></span>
                  Counterparty Enquiry
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="help-circle"></span>
                  Help
                </a>
              </li>
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Saved reports</span>
              <a class="d-flex align-items-center text-muted" href="#">
                <span data-feather="plus-circle"></span>
              </a>
            </h6>
            <ul class="nav flex-column mb-2">
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Current month
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Last month
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        	<tiles:insertAttribute name="content" />
        </main>
      </div>
    </div>
    
    

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="http://bootstrap.hexschool.com/assets/js/vendor/jquery-slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="http://twitter.github.io/typeahead.js/releases/latest/typeahead.bundle.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular.min.js"></script>

    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

	<script type="text/javascript">
		$(document).ready(
				function() {
					// Defining the local dataset
					var opasFunctions = [ 'Counterparty Enquiry',
						                  'First Level Check',
						                  'Exception Check',
						                  'Deal Enquiry',
						                  'User Settings',
						                  'Business Volume Dashboard',
						                  'Logout' ];

					// Constructing the suggestion engine
					var opasFunctions = new Bloodhound({
						datumTokenizer : Bloodhound.tokenizers.whitespace,
						queryTokenizer : Bloodhound.tokenizers.whitespace,
						local : opasFunctions
					});

					// Initializing the typeahead
					$('#userSearch').typeahead({
						hint : true,
						highlight : true, /* Enable substring highlighting */
						minLength : 1
					/* Specify minimum characters required for showing suggestions */
					}, {
						name : 'opasFunctions',
						source : opasFunctions
					});
				});
	</script>

</body>
</html>
