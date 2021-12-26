<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<br>
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'usersDwarf'}" url="/usersDwarf/find"
					title="find users">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Find users</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'statistics'}" url="/statistics"
					title="statistics">
					<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
					<span>Statistics</span>
				</petclinic:menuItem>

			

				<petclinic:menuItem active="${name eq 'achievements'}" url="/achievements/findAchievements"
					title="achievements">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Achievements</span>
				</petclinic:menuItem>
				<petclinic:menuItem active="${name eq 'vets'}" url="/game/new"
					title="new game">
					<span class="glyphicon glyphicon-tower" aria-hidden="true"></span>
					<span>Create Game</span>
				</petclinic:menuItem>
			</ul>

			<ul class="nav navbar-nav navbar-right">
                <sec:authorize access="!isAuthenticated()">
					<li>
						<div class="dropdown">
							<button class="btn btn-navbar btn-block dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="../resources/images/unnamed.png" height ="50" width="50"/>
							</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item">
									<button type="button" class="btn btn-login btn-block"
                            			onClick='redirectOnClickRegister()'>Register</button>
								</a>
								<a class="dropdown-item" href="#">
									<button type="button" class="btn btn-login btn-block"
                           				 onClick='redirectOnClickLogin()'>Login</button>
								</a>
							</div>
						</div>
					</li>
                </sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="<c:url value="/logout" />"><b>LOGOUT</b></a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>

	<script>
        function redirectOnClickLogin() {
            document.location = "/login";
        }
    </script>
    <script>
        function redirectOnClickRegister() {
            document.location = "/usersDwarf/register";
        }
    </script>
</nav>
