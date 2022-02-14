package com.ngocnb20.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ngocnb20.travel.model.entity.Place;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "image_places")
public class ImagePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="id_place",referencedColumnName = "id")
    //@JsonBackReference(value = "imagePlaces")
    private Place place;
}
