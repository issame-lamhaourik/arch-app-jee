package myboot.app5.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSecuritySpring {

    private RestTemplate restTemplate = new RestTemplate();
    private String API_URL = "http://localhost:8081/secu-users";

    @Test
    public void testLogin() throws Exception {
        String loginUrl = API_URL + "/login?username=aaa&password=aaa";
        ResponseEntity<String> loginResponse = restTemplate.postForEntity(loginUrl, null, String.class);
        String jwtToken = loginResponse.getBody();
        System.out.println(jwtToken);
    }
    @Test
    public void testGetUserInfo() {
        String loginUrl = API_URL + "/login?username=aaa&password=aaa";
        ResponseEntity<String> loginResponse = restTemplate.postForEntity(loginUrl, null, String.class);
        String jwtToken = loginResponse.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String meUrl = API_URL + "/me";
        ResponseEntity<String> meResponse = restTemplate.exchange(meUrl, HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, meResponse.getStatusCode());
        System.out.println("User Info: " + meResponse.getBody());
    }
    //test
}
