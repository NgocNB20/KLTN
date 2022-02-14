package com.ngocnb20.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "blogs")
public class Blog extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_view")
    private int numberView;
    @Column(name = "number_comment")
    private int numberComment;
    @Column(name = "number_like")
    private int numberLike;
    @Column(name = "date_blog")
    private LocalDate date;
    private String image;
    @Column(columnDefinition = "nvarchar(255)")
    private String title;
    @Column(columnDefinition = "nvarchar(Max)")
    private String detail;
    @Column(name = "datail_summary",columnDefinition = "nvarchar(Max)")
    private String detailSummary;

    @ManyToOne()
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    @JsonIgnore
    private Member member;



}
