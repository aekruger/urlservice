package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.KeywordDTO;
import com.krueger.urlservice.model.Keyword;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordMapperTest {

    @Test
    void toEntity() {
        List<KeywordDTO> keywordsDTO = new ArrayList<>();
        keywordsDTO.add(new KeywordDTO("keyword"));
        List<Keyword> keywords = KeywordMapper.toEntityList(keywordsDTO);
        assertNotNull(keywords);
        assertEquals(1, keywords.size());
        assertEquals("keyword", keywords.get(0).getKeyword());
    }
}