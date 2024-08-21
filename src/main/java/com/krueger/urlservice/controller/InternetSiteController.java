package com.krueger.urlservice.controller;

import com.krueger.urlservice.dto.InternetSiteDTO;
import com.krueger.urlservice.mapper.InternetSiteMapper;
import com.krueger.urlservice.model.InternetSite;
import com.krueger.urlservice.service.InternetSiteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<InternetSiteDTO>> findAll() {
        List<InternetSite> all = service.findAll();
        List<InternetSiteDTO> list = all.stream().map(site -> InternetSiteMapper.toDTO(site)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/paged/")
    public Page<InternetSiteDTO> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<InternetSite> internetSitePage = service.findAllPaged(page, size);
        return internetSitePage.map(site -> InternetSiteMapper.toDTO(site));
    }


}
