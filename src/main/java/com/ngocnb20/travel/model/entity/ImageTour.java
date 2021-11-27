package com.ngocnb20.travel.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "Tours")
public class ImageTour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    @ManyToOne
    @JoinColumn(name="tour_id",referencedColumnName = "id")
    private Tour tour =new Tour();
}
