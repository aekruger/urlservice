package com.krueger.urlservice.repository;

import com.krueger.urlservice.model.InternetSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternetSiteRepos extends JpaRepository<InternetSite, Long> {
}
