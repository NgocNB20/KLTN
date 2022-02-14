package com.ngocnb20.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "tours")
public class Tour extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String title;
    private String image;
    @Column(name = "number_view")
    private int numberView;
    @Column(name = "total_like")
    private int numberLike;
    private int rate;    //đánh giá mấy sao
    @Column(columnDefinition = "nvarchar(Max)")
    private String detail;
    @Column(name = "sub_detail",columnDefinition = "nvarchar(Max)")
    private String subDetail;
    private double price;
    @Column(name = "total_day",columnDefinition = "nvarchar(50)")
    private String totalDay;  //5 ngày 4 đêm
    @Column(name = "total_person")
    private int totalPerson;
    @Column(columnDefinition = "nvarchar(255)")
    private String departure;   //địa điểm khởi hành

    @OneToMany(mappedBy = "tour")
    private Set<ImageTour> imageTours=new HashSet<>();
    //@JsonManagedReference(value = "imageTours")

    @ManyToOne()
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    @JsonIgnore
    private Member member;
}
