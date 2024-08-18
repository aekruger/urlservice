package com.krueger.urlservice.dto;

import java.util.List;

public record InternetSiteDTO (String name, List<KeywordDTO> keywords, List<SitePageDTO> sitePages) {
}
