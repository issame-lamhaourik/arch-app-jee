<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<h1>Confirmer la suppression</h1>
	<p>Voulez-vous vraiment supprimer le film <strong>${movie.name}</strong> (Annee : ${movie.year}) ?</p>

	<form action="${pageContext.request.contextPath}/deleteMovie/${movie.id}" method="post">
		<input type="submit" value="Oui, supprimer" class="btn btn-danger" />
		<a href="${pageContext.request.contextPath}/movies" class="btn btn-secondary">Annuler</a>
	</form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
