package com.krueger.urlservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternetSite extends BaseEntity {

    String name;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Keyword> keywords;
    @OneToMany (mappedBy = "internetSite", cascade = CascadeType.ALL)
    List<SitePage> pages;
}
