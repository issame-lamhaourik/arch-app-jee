<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
	<h1 class="p-3"></h1>

	<table class="table">
		<tr>
			<th>Nom :</th>
			<td>${movie.name}</td>
		</tr>
		<tr>
			<th>Annee :</th>
			<td>${movie.year}</td>
		</tr>
		<tr>
			<th>Description :</th>
			<td>${movie.description}</td>
			<td></td>
		</tr>
	</table>

	<c:url var="editAction" value="/edit-movie" />
	<c:url var="moviesAction" value="/movies" />

	<p>
		<a class="btn btn-primary btn-sm" href="${editAction}/${movie.id}">Modifier</a>
		<a class="btn btn-primary btn-sm" href="${moviesAction}">Liste des films</a>
	</p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
