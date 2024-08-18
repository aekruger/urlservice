package com.krueger.urlservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SitePage extends BaseEntity {
    String name;
    String description;
    String url;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Keyword> keywords;
    @ManyToOne
    InternetSite internetSite;
}
