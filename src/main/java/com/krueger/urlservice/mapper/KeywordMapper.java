package com.krueger.urlservice.mapper;

import com.krueger.urlservice.dto.KeywordDTO;
import com.krueger.urlservice.model.Keyword;

import java.util.List;

public class KeywordMapper {
    public static List<Keyword> toEntityList(List<KeywordDTO> keywords) {
        return keywords.parallelStream().map(keywordDTO -> toEntity(keywordDTO)).toList();
    }

    public static Keyword toEntity(KeywordDTO keywordDTO) {
        return new Keyword(keywordDTO.keyword());
    }

    public static List<KeywordDTO> toDTOList(List<Keyword> keywords) {
        return keywords.parallelStream().map(keyword -> toDTO(keyword)).toList();
    }

    public static KeywordDTO toDTO(Keyword keyword) {
        return new KeywordDTO(keyword.getKeyword());
    }

}
