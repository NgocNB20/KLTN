package com.ngocnb20.travel.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name ="agencies" )
public class Agencies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String address;
    private String type;
    private String email;
    @Column(name = "url_web")
    private String urlWeb;
    private String phone;
    private String detail;

}
