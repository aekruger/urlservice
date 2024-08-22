package com.krueger.urlservice;

import com.krueger.urlservice.dto.InternetSiteDTO;
import com.krueger.urlservice.dto.KeywordDTO;
import com.krueger.urlservice.dto.SitePageDTO;
import com.krueger.urlservice.dto.URLServiceRequest;
import com.krueger.urlservice.dto.URLServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
class HttpRequestTest {

    public static final String ORIGINAL_URL = "http://example.com";
    public static final String SHORTEN_URL = "http://localhost:8080/shorten-iq14il";
    public static final String URL_SERVICE_SHORTEN = "/url-service/shorten";
    public static final String URL_SERVICE_ORIGINAL = "/url-service/original";
    public static final String INTERNET_SITE = "/internet-site/";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port + "/api";
    }

    @Test
    public void testShortenURL() {
        // Arrange
        URLServiceRequest request = new URLServiceRequest(ORIGINAL_URL);

        // Act
        ResponseEntity<URLServiceResponse> response =
                restTemplate.postForEntity(baseUrl + URL_SERVICE_SHORTEN, request, URLServiceResponse.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUrl()).isEqualTo(SHORTEN_URL);
    }

    @Test
    public void testOriginalURL() {
        // Given
        URLServiceRequest request = new URLServiceRequest(ORIGINAL_URL);
        restTemplate.postForEntity(baseUrl + URL_SERVICE_SHORTEN, request, URLServiceResponse.class);

        // When
        ResponseEntity<URLServiceResponse> response =
                restTemplate.getForEntity(baseUrl + URL_SERVICE_ORIGINAL + "?url=" +SHORTEN_URL, URLServiceResponse.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUrl()).isEqualTo(ORIGINAL_URL);
    }

    @Test
    public void testCreateInternetSite() {
        // Given
        InternetSiteDTO internetSiteDTO = getInternetSiteDTO();

        // When
        ResponseEntity<InternetSiteDTO> response =
                restTemplate.postForEntity(baseUrl + INTERNET_SITE, internetSiteDTO, InternetSiteDTO.class);

        log.info(response.getBody().toString());
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().name()).isEqualTo("name");
    }

    private static InternetSiteDTO getInternetSiteDTO() {
        ArrayList<KeywordDTO> keywordDTOS = new ArrayList<>();
        keywordDTOS.add(new KeywordDTO("keyword"));
        ArrayList<SitePageDTO> sitePageDTOS = new ArrayList<>();
        sitePageDTOS.add(new SitePageDTO("name", "desc", "url", keywordDTOS));
        InternetSiteDTO internetSiteDTO = new InternetSiteDTO("name", keywordDTOS, sitePageDTOS);
        return internetSiteDTO;
    }

    @Test
    public void testFindAllInternetSite() {
        // Given
        InternetSiteDTO internetSiteDTO = getInternetSiteDTO();
        restTemplate.postForEntity(baseUrl + INTERNET_SITE, internetSiteDTO, InternetSiteDTO.class);

        // When
        ResponseEntity<InternetSiteDTO[]> response =
                restTemplate.getForEntity(baseUrl + INTERNET_SITE, InternetSiteDTO[].class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        List<InternetSiteDTO> internetSiteDTOS = Arrays.stream(response.getBody()).toList();
        log.info("Response: {}", internetSiteDTOS);
        assertThat(internetSiteDTOS.size()).isEqualTo(1);
        assertThat(internetSiteDTOS.getFirst().name()).isEqualTo("name");


    }

}