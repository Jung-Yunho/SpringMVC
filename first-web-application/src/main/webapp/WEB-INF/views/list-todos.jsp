<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<br />
	<table class="table table-striped">
		<caption><spring:message code="todo.caption"/></caption>
		<thead>
			<tr>
				<th><spring:message code="description.caption"/></th>
				<th><spring:message code="targetDate.caption"/></th>
				<th><spring:message code="isCompleted.caption"/></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td><fmt:formatDate value="${todo.targetDate}"
							pattern="yyyy/MM/dd" /></td>
					<td>${todo.done}</td>
					<td><a class="btn btn-success"
						href="/update-todo?id=${todo.id}">Update</a></td>
					<td><a class="btn btn-danger"
						href="/delete-todo?id=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div>
		<a class="btn btn-success" href="/add-todo">Add</a>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
<!-- Valid User -> welcome.jsp -->
<!-- InValid User -> login.jsp -->