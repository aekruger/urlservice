package com.krueger.urlservice.controller;

import com.krueger.urlservice.dto.InternetSiteDTO;
import com.krueger.urlservice.mapper.InternetSiteMapper;
import com.krueger.urlservice.model.InternetSite;
import com.krueger.urlservice.service.InternetSiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/internet-site")
public class InternetSiteController {

    private final InternetSiteService service;

    public InternetSiteController(InternetSiteService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<InternetSiteDTO> create (@RequestBody InternetSiteDTO dto) {
        InternetSite site = InternetSiteMapper.toEntity(dto);
        InternetSite siteDB = service.create(site);
        return ResponseEntity.ok(InternetSiteMapper.toDTO(siteDB));
    }


}
