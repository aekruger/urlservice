package com.krueger.urlservice.service;

import com.krueger.urlservice.dto.InternetSiteDTO;
import com.krueger.urlservice.model.InternetSite;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InternetSiteService {

    InternetSite create (InternetSite site);

    Page<InternetSite> findAllPaged(int page, int size);

    List<InternetSite> findAll();
}
