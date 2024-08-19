package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.SitePageDTO;
import com.krueger.urlservice.model.SitePage;

import java.util.List;

public class SitePageMapper {
    public static List<SitePage> toEntityList(List<SitePageDTO> sitePageDTOS) {
        return sitePageDTOS.parallelStream().map(
                sitePageDTO -> toEntity(sitePageDTO))
                .toList();
    }

    public static SitePage toEntity(SitePageDTO dto) {
        return SitePage.builder()
                .name(dto.name())
                .description(dto.description())
                .url(dto.url())
                .keywords(KeywordMapper.toEntityList(dto.keywords()))
                .build();
    }

    public static List<SitePageDTO> toDTOList(List<SitePage> pages) {
        return pages.parallelStream().map(sitePage -> toDTO(sitePage)).toList();
    }

    public static SitePageDTO toDTO(SitePage sitePage) {
        return new SitePageDTO(
                sitePage.getName(),
                sitePage.getDescription(),
                sitePage.getUrl(),
                KeywordMapper.toDTOList(sitePage.getKeywords())
        );
    }
}
