package com.krueger.urlservice.service;

import com.krueger.urlservice.model.InternetSite;
import com.krueger.urlservice.repository.InternetSiteRepos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
