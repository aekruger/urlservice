package com.krueger.urlservice.repository;

import com.krueger.urlservice.model.URLMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepos extends JpaRepository<URLMapping, String> {
}
