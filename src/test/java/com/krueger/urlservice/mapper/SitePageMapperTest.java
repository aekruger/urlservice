package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.SitePageDTO;
import com.krueger.urlservice.model.SitePage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SitePageMapperTest {

    @Test
    void toEntityList() {
        List<SitePageDTO> dtos = new ArrayList<>();
        dtos.add(getDto());
        List<SitePage> entityList = SitePageMapper.toEntityList(dtos);
        assertNotNull(entityList);
        assertEquals(dtos.size(), entityList.size());
    }

    @Test
    void toEntity() {
        SitePageDTO dto = getDto();
        SitePage page = SitePageMapper.toEntity(dto);
        assertNotNull(page);
        assertEquals(dto.name(), page.getName());
    }

    private static SitePageDTO getDto() {
        return new SitePageDTO("name", "desc", "url", new ArrayList<>());
    }

    @Test
    void toDTOList() {
        List<SitePage> entityList = new ArrayList<>();
        entityList.add(getSitePage());
        List<SitePageDTO> dtos = SitePageMapper.toDTOList(entityList);
        assertNotNull(dtos);
        assertEquals(entityList.size(), dtos.size());
    }

    @Test
    void toDTO() {
        SitePage page = getSitePage();
        SitePageDTO dto = SitePageMapper.toDTO(page);
        assertNotNull(dto);
        assertEquals(page.getName(), dto.name());

    }

    private static SitePage getSitePage() {
        return SitePage.builder()
                .name("name")
                .description("desc")
                .url("url")
                .keywords(new ArrayList<>())
                .build();
    }
}