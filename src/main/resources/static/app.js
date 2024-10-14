const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            counter: 1,
            step:1,
            message: "Hello",
            list: [10, 20, 30],
            axios: null,
            movies: null,
            id: 0,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/api/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
        this.displayMovie();
    },

    methods: {
        // Place pour les futures méthodes
        incCounter: function() {
            console.log("incremente le compteur ");
            this.counter++;
            this.step = this.step + 2;
            this.axios.get('/movies/1')
                .then(r => {
                    console.log("read movie 1 done");
                    this.message = r.data;
                });
        },
        displayMovie: function() {
            this.axios.get('/movies')
                .then(r => {
                    console.log("Films récupérés:", r.data);
                    this.movies = r.data;
                })
        },
        deleteMovie: function(index){
            const id = this.movies[index].id;
            this.axios.delete(`/movies/${id}`)
            .then(r => {
                this.displayMovie();
            })
        },
        showMovie: function(index){
            const id = this.movies[index].id;
            this.axios.get(`/movies/${id}`)
            .then(response => {response.data;
            })
        },
        editMovie: function(index) {
            const id = this.movies[index].id;
            window.location.href = `/edit-movie/${id}`
        },
    }
}

Vue.createApp(myApp).mount('#myApp');