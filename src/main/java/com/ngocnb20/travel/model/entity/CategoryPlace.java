package com.ngocnb20.travel.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "category_places")
public class CategoryPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy ="categoryPlaces")
    private Set<Place> places=new HashSet<>();

    public CategoryPlace(String name, Set<Place> places) {
        this.name = name;
        this.places = places;
    }

    public CategoryPlace(String name) {
        this.name = name;
    }
}
