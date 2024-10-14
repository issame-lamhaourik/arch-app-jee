<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
	<h1 class="p-3">Liste des films avant 2000</h1>

	<c:url var="movieAction" value="/movie" />
	<c:url var="confirmDeleteAction" value="/confirmDelete" />

	<table class="table">
		<tr>
			<th>Nom</th>
			<th>Annee</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="movie" items="${oldmovie}">
			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td><a class="btn btn-primary btn-sm"
					href="${movieAction}/${movie.id}">Montrer</a></td>
				<td><a class="btn btn-danger btn-sm"
					href="${confirmDeleteAction}/${movie.id}">Supprimer</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
