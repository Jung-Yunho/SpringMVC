<%@ include file="common/header.jspf"%>
<nav role="navigation" class="navbar navbar-default">
	<div class="">
		<a href="http://www.in28minutes.com" class="navbar-brand">in28Minutes</a>
	</div>
	<div class="navbar-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="/">Home</a></li>
			<li><a href="/list-todos">Todos</a></li>

		</ul>
	</div>
</nav>
<div class="container">
	<spring:message code="welcome.caption"/> ${name} <br /> Your Password is ${password} <br /> Now, You
	can <a href="/list-todos">manage your todos.</a>
</div>
<%@ include file="common/footer.jspf"%>
<!-- Valid User -> welcome.jsp -->
<!-- InValid User -> login.jsp -->