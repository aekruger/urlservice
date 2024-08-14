package com.krueger.urlservice.service;

import com.krueger.urlservice.config.URLServiceProperties;
import com.krueger.urlservice.model.URLMapping;
import com.krueger.urlservice.repository.URLRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class URLServiceImpl implements URLService{

    private final URLRepos repository;
    private final URLServiceProperties properties;

    public URLServiceImpl(URLRepos repository, URLServiceProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    @Cacheable(value = "urlservice", key = "#originalUrl")
    public URLMapping shortenUrl(String originalUrl) {
        log.info("Original url {}", originalUrl);

        URLMapping entity = URLMapping.
                builder().
                original(originalUrl).
                shorten(getURL() + Long.toString(originalUrl.hashCode(),32)).
                build();
        return repository.save(entity);
    }

    @Cacheable(value = "urlservice", key = "#shortUrl")
    public Optional<URLMapping> getOriginalUrl(String shortUrl) {
        log.info("Shorten url {}", shortUrl);
        return repository.findById(shortUrl);
    }

    private String getURL(){
        try {
            URL url = new URL(properties.getProtocol(), properties.getHost(), properties.getPort(), properties.getDomain());
            return url.toString();
        } catch (MalformedURLException e) {
            return  "" ;
        }
    }

}
