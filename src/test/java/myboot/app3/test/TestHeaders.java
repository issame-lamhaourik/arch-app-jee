package myboot.app3.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestHeaders {

   
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testHeaders() {
        String url = "http://localhost:8081/api/headers";

        HttpHeaders headers = new HttpHeaders();
        headers.add("myHeader", "myHeaderValue");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isEqualTo("HEADER myHeaderValue");
        HttpHeaders responseHeaders = response.getHeaders();
        assertThat(responseHeaders.getFirst("resultHeader")).isEqualTo("MYHEADERVALUE");
        assertThat(responseHeaders.getFirst("xx")).isEqualTo("yy");
    }

}
