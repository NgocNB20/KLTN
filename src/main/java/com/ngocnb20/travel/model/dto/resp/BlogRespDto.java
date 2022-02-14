package com.ngocnb20.travel.model.dto.resp;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogRespDto {

    private Long id;
    private int numberView;
    private int numberComment;
    private int numberLike;
    private LocalDate date;
    private String image;
    private String title;
    private String detailSummary;
    private String detail;

    //private String themmoi;///deo co entity

}
