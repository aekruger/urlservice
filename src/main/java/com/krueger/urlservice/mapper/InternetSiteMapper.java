package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.InternetSiteDTO;
import com.krueger.urlservice.dto.KeywordDTO;
import com.krueger.urlservice.dto.SitePageDTO;
import com.krueger.urlservice.model.InternetSite;
import com.krueger.urlservice.model.Keyword;
import com.krueger.urlservice.model.SitePage;

import java.util.List;

public class InternetSiteMapper {
    public static InternetSite toEntity(InternetSiteDTO dto) {
        List<Keyword> keywords = KeywordMapper.toEntityList(dto.keywords());
        List<SitePage> pages = SitePageMapper.toEntityList(dto.sitePages());
        InternetSite site = InternetSite.builder()
                .name(dto.name())
                .keywords(keywords)
                .pages(pages)
                .build();
        return site;
    }

    public static InternetSiteDTO toDTO(InternetSite siteDB) {
        List<KeywordDTO> keywords = KeywordMapper.toDTOList(siteDB.getKeywords());
        List<SitePageDTO> sitePages = SitePageMapper.toDTOList(siteDB.getPages());
        InternetSiteDTO dto = new InternetSiteDTO(siteDB.getName(), keywords, sitePages);
        return dto;
    }
}
