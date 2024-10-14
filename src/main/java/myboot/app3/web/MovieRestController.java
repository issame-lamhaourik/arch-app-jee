package myboot.app3.web;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import myboot.app3.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    MovieRepository repo;
    @JsonView(View.Public.class)
//    @GetMapping("/movies")
//    public Iterable<Movie> getMovies() {
//        return repo.findAll();
//    }
    @GetMapping("/movies")
    public Iterable<Movie> getMovies(
        @RequestParam(required = false, defaultValue = "%") String name,
        @RequestParam(required = false, defaultValue = "0") int year) {
        List<Movie> movies = new ArrayList<Movie>();

        return repo.findWithFilters(name,year);
    }

    @JsonView(View.Public.class)
//    @GetMapping("/movies/{id}")
//    public Movie getMovie(@PathVariable int id) {
//        return repo.findById(id).get();
//    }
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id) {
        Optional<Movie> movie = repo.findById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //suppr
    @DeleteMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(@PathVariable int id) {
        repo.deleteById(id);
    }

    //ajouter
    @PostMapping("/movies")
    public Movie postMovie(@Valid @RequestBody Movie m) {
        repo.save(m);
        return m;
    }

//    @PutMapping("/movies/{id}")
//    public Movie putMovie(@PathVariable int id, @Valid @RequestBody Movie m) {
//        Movie movie = repo.findById(id)
//                .orElseThrow(() -> new MovieNotFoundException());
//        movie.setName(m.getName());
//        movie.setYear(m.getYear());
//        movie.setDescription(m.getDescription());
//        return repo.save(movie);
//    }
@PutMapping("/movies/{id}")
public ResponseEntity<Movie> putMovie(@PathVariable int id, @RequestBody Movie updatedMovie) {
    if (!repo.existsById(id)) {
        return ResponseEntity.notFound().build();
    }
    updatedMovie.setId(id);
    Movie savedMovie = repo.save(updatedMovie);
    return ResponseEntity.ok(savedMovie);
}





}