package com.krueger.urlservice.controller;

import com.krueger.urlservice.model.URLMapping;
import com.krueger.urlservice.dto.URLServiceRequest;
import com.krueger.urlservice.dto.URLServiceResponse;
import com.krueger.urlservice.service.URLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/url-service")
public class URLController {

    private final URLService service;

    public URLController(URLService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<URLServiceResponse> shortenURL(@RequestBody URLServiceRequest request){
        URLMapping shorUrl = service.shortenUrl(request.getOriginalURL());
        return ResponseEntity.ok(new URLServiceResponse(shorUrl.getShorten()));
    }

    @GetMapping("/original")
    public ResponseEntity<URLServiceResponse> originalURL(@RequestParam String url){
        Optional<URLMapping> originalUrl = service.getOriginalUrl(url);
        return ResponseEntity.ok(new URLServiceResponse(originalUrl.get().getOriginal()));
    }

}
