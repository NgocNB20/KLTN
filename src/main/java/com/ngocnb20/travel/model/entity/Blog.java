package com.ngocnb20.travel.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int view;
    private int comment;
    private int like;
    private LocalDate date;
    private String image;
    private String title;
    private String detail;

}
