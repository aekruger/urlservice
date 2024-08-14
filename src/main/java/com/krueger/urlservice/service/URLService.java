package com.krueger.urlservice.service;

import com.krueger.urlservice.model.URLMapping;

import java.util.Optional;

public interface URLService {
    URLMapping shortenUrl(String originalUrl);
    Optional<URLMapping> getOriginalUrl(String shortUrl);
}
