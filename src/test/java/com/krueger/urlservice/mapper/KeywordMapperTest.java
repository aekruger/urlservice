package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.KeywordDTO;
import com.krueger.urlservice.model.Keyword;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class KeywordMapperTest {

    public static final int BIGLIST = 1000000;

    @Test
    void toEntity() {
        Keyword keyword = KeywordMapper.toEntity(new KeywordDTO("keyword"));
        assertNotNull(keyword);
        assertEquals("keyword", keyword.getKeyword());
    }

    @Test
    void toDTO() {
        KeywordDTO dto = KeywordMapper.toDTO(new Keyword("keyword"));
        assertNotNull(dto);
        assertEquals("keyword", dto.keyword());
    }

    @Test
    void toEntityList() {
        List<KeywordDTO> keywordsDTO = new ArrayList<>();
        keywordsDTO.add(new KeywordDTO("keyword"));
        List<Keyword> keywords = KeywordMapper.toEntityList(keywordsDTO);
        assertNotNull(keywords);
        assertEquals(keywordsDTO.size(), keywords.size());
        assertEquals("keyword", keywords.get(0).getKeyword());
    }

    @Test
    void toDTOList() {
        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("keyword"));
        List<KeywordDTO> keywordDTOs = KeywordMapper.toDTOList(keywords);
        assertNotNull(keywordDTOs);
        assertEquals(keywords.size(), keywordDTOs.size());
        assertEquals("keyword", keywordDTOs.get(0).keyword());
    }

    @Test
    void toDTOBigList() {
        List<Keyword> keywords = new ArrayList<>();
        IntStream.range(0, BIGLIST)
                .forEach(index -> {
                    keywords.add(new Keyword("keyword"+index));
                });
        List<KeywordDTO> keywordDTOs = KeywordMapper.toDTOList(keywords);
        assertNotNull(keywordDTOs);
        assertEquals(BIGLIST, keywordDTOs.size());
        assertEquals("keyword0", keywordDTOs.get(0).keyword());
    }

}