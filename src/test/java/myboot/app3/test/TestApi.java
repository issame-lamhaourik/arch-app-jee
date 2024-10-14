package myboot.app3.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestApi {
    @Test
    public void testHello() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/hello";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testList() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/list";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testHello2() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/hello2";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testNotFound() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/notFound";
        ResponseEntity<Void> response = restTemplate.getForEntity(fooResourceUrl, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testNoContent() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/noContent";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    public void testMovieRestController() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/movies";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testMovie1RestController() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/api/movies/1";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}