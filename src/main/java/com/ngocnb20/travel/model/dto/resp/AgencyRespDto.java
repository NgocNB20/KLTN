package com.ngocnb20.travel.model.dto.resp;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyRespDto {
    private Long id;
    private String image;
    private String address;
    //private String typeAgency;
    private String email;
    private String urlWeb;
    private String phone;
    private String detail;
    private int numberView;
    private int numberLike;
}
