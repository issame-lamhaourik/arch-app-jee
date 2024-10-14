<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/app" />
<c:url var="app" value="/app.js" />


<div id="myApp">
    <div class="container">
        <h1>My application</h1>
        <p>{{ message }}</p>
        <p>list = <span v-for="element in list">{{element}} - </span></p>
        <p>counter = {{counter}}</p>
        <p> <button v-on:click="incCounter">Plus un</button></p>
        <span v-on:mouseover="incCounter">Il faut me survoler</span>
        <p>counter = {{step}}</p>
        <p> <button v-on:click="incCounter(0)">Plus deux</button></p>

<!--
        <p>movies = <span v-for="element in movies">{{ element }} -
		</span></p>
-->

        <h2>Liste des films</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Annee</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(movie, index) in movies" :key="index">
                    <td>{{ movie.name }}</td>
                    <td>{{ movie.year }}</td>
                    <td>
                        <button class="btn btn-danger btn-sm" @click="deleteMovie(movie.id)">Supprimer</button>
                    </td>
                </tr>
            </tbody>
        </table>
</div>

<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>