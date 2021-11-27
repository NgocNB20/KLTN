package com.ngocnb20.travel.model.entity;

import lombok.*;
import model.entity.ImagePlace;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_place")
    private Long id;
    private String name;
    private String address;
    private String price;
    private int view;
    private int comment;
    private int like;
    private String detail;
    private String image;
    @OneToMany(mappedBy = "place")
    private Set<ImagePlace> imagePlaces;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="place_categorys",joinColumns = {@JoinColumn( name="place_id")},
                inverseJoinColumns = {@JoinColumn(name = "category_image_id")})
    private Set<CategoryPlace> categoryPlaces=new HashSet<>();

    public Place(String name, Set<CategoryPlace> categoryPlaces) {
        this.name = name;
        this.categoryPlaces = categoryPlaces;
    }

    public Place(String name) {
        this.name = name;
    }
}
