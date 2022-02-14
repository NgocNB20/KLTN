package com.ngocnb20.travel.model.dto.resp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ngocnb20.travel.model.entity.CategoryPlace;
import com.ngocnb20.travel.model.entity.ImagePlace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRespDto implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String price;
    private int numberView;
    private int numberLike;
    private int numberComment;
    private String detail;
    private String image;
    private Set<ImagePlaceDto> imagePlaces;
    private Set<CategoryDto> categories;

    //private Set<CategoryPlace> categories=new HashSet<>();

}
