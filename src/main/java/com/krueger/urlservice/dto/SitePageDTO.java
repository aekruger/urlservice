package com.krueger.urlservice.dto;

import java.util.List;

public record SitePageDTO(String name, String description, String url, List<KeywordDTO> keywords) {
}
