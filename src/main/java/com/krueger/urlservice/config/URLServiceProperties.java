package com.krueger.urlservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties( prefix = "urlservice")
@Data
public class URLServiceProperties {
    public final String SLASH = "/";
    public final String COLON = ":";
    String protocol = "http";
    String host = "localhost";
    int port = 8080;
    String domain = "/shorturl";

}

