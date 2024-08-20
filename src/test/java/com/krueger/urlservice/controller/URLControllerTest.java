package com.krueger.urlservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krueger.urlservice.dto.URLServiceRequest;
import com.krueger.urlservice.model.URLMapping;
import com.krueger.urlservice.service.URLService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(URLController.class)
class URLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private URLService urlService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testShortenURL() throws Exception {
        // Given
        URLServiceRequest request = new URLServiceRequest("http://example.com");
        URLMapping urlMapping = new URLMapping("http://example.com", "http://short.ly/abc123");
        Mockito.when(urlService.shortenUrl(anyString())).thenReturn(urlMapping);

        // Then
        mockMvc.perform(post("/api/url-service/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://short.ly/abc123"));
    }

    @Test
    public void testOriginalURL() throws Exception {
        // Given
        URLMapping urlMapping = new URLMapping("http://example.com", "http://short.ly/abc123");
        Mockito.when(urlService.getOriginalUrl(anyString())).thenReturn(Optional.of(urlMapping));

        // Then
        mockMvc.perform(get("/api/url-service/original")
                        .param("url", "http://short.ly/abc123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("http://example.com"));
    }

}