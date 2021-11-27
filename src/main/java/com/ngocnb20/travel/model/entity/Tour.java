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
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String image;
    private int view;
    private int rate;
    private int like;
    private String detail;
    private double price;
    private String totalDay;
    private int totalPerson;
    private String departure;
    @OneToMany(mappedBy = "tour")
    private Set<ImageTour> imageTours=new HashSet<>();
}
