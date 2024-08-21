package com.krueger.urlservice.service;

import com.krueger.urlservice.model.InternetSite;
import com.krueger.urlservice.repository.InternetSiteRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InternetSiteServiceImpl implements InternetSiteService {

    private final InternetSiteRepos repository;

    public InternetSiteServiceImpl(InternetSiteRepos repository) {
        this.repository = repository;
    }

    @Override
    public InternetSite create(InternetSite site) {
        setInitialDates(site);
        site.getPages().parallelStream().forEach(
                sitePage -> {
                    sitePage.setInternetSite(site);
                }
        );
        return repository.save(site);
    }

    @Override
    public Page<InternetSite> findAllPaged(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<InternetSite> findAll() {
        return repository.findAll();
    }

    private static void setInitialDates(InternetSite site) {
        site.setCreatedOn(LocalDate.now());
        site.setUpdatedOn(LocalDate.now());
        site.getKeywords().parallelStream().forEach(
                keyword -> {
                    keyword.setCreatedOn(LocalDate.now());
                    keyword.setUpdatedOn(LocalDate.now());
                });
        site.getPages().parallelStream().forEach(
                sitePage -> {
                    sitePage.setCreatedOn(LocalDate.now());
                    sitePage.setUpdatedOn(LocalDate.now());
                }
        );
    }

}
