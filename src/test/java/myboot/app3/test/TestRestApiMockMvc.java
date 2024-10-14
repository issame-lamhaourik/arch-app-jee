package myboot.app3.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRestApiMockMvc {

    @Autowired
    private MockMvc mvc;


    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private MovieRepository movieRepository;


    @Test
    public void testHello() throws Exception {
        mvc.perform(get("/api/hello")).andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(content().string("Hello"));
    }

    @Test
    public void testnotFound() throws Exception {
        mvc.perform(get("/api/notFound")).andDo(print())//
                .andExpect(status().isNotFound())//
                .andExpect(content().string(""));
    }

    @Test
    public void testnotContent() throws Exception {
        mvc.perform(get("/api/noContent")).andDo(print())//
                .andExpect(status().isNoContent())//
                .andExpect(content().string(""));
    }

    @Test
    public void testList() throws Exception {
        mvc.perform(get("/api/list")).andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(content().json("[10,20,30]"));
    }
    @Test
    public void testHeaders() throws Exception {
        String headerValue= "testValue";
        mvc.perform(get("/api/headers").header("myHeader", headerValue))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("resultHeader", headerValue.toUpperCase()))
                .andExpect(header().string("xx", "yy"))
                .andExpect(content().string("HEADER " + headerValue));
    }

    //*******************************************************************************************************************
    @Test
    public void testAddInvalidMovie() throws Exception {
        String invalidMovieJson = "{ \"name\": \"\", \"year\": 1800, \"description\": \"A film\" }"; //nom vide, et année inf a 1900 donc invalid
        mvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidMovieJson))
                .andExpect(status().isBadRequest()); // Vérifie le statut 400
    }

    @Test
    public void testRestTemplateMovies() throws Exception {
        ResponseEntity<Movie[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8081/api/list/",
                        Movie[].class);
        Movie[] movies = response.getBody();
        Assertions.assertThat(movies).isNotNull();  }
    //*******************************************************************************************************************

    @Test
    void testDeleteMovie() throws Exception {
        int movieId = 1;
        mvc.perform(delete("/movies/{id}", movieId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatedMovie() throws Exception {
        // Création d'un film
        Movie movie = new Movie("La premier diamant", 2024, "Au cours d'une exploration archéologique, des chercheurs mettent la main sur une mine de diamants...");
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "http://localhost:8081/movies";

        ResponseEntity<Movie> response = restTemplate.postForEntity(url, movie, Movie.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert response.getBody().getName().equals(movie.getName()); // Utilisation de getName()
        assert response.getBody().getYear() == movie.getYear();
        assert response.getBody().getDescription().equals(movie.getDescription());

    }


}