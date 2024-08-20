package com.krueger.urlservice;

import com.krueger.urlservice.dto.URLServiceRequest;
import com.krueger.urlservice.dto.URLServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

    public static final String ORIGINAL_URL = "http://example.com";
    public static final String SHORTEN_URL = "http://localhost:8080/shorten-iq14il";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port + "/api/url-service";
    }

    @Test
    public void testShortenURL() {
        // Arrange
        URLServiceRequest request = new URLServiceRequest(ORIGINAL_URL);

        // Act
        ResponseEntity<URLServiceResponse> response = restTemplate.postForEntity(baseUrl + "/shorten", request, URLServiceResponse.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUrl()).isEqualTo(SHORTEN_URL);
    }

    @Test
    public void testOriginalURL() {
        // Given
        URLServiceRequest request = new URLServiceRequest(ORIGINAL_URL);
        restTemplate.postForEntity(baseUrl + "/shorten", request, URLServiceResponse.class);

        // When
        ResponseEntity<URLServiceResponse> response = restTemplate.getForEntity(baseUrl + "/original?url="+SHORTEN_URL, URLServiceResponse.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUrl()).isEqualTo(ORIGINAL_URL);
    }


}