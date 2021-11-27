package com.ngocnb20.travel.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "Hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private int view;
    private int rate;
    private int like;
    private String address;
    private String type;
    private String email;
    @Column(name = "url_web")
    private String urlWeb;
    private String phone;
    private String detail;
    private double price;
    private String service;



}
