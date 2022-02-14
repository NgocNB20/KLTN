package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.BaseRespDto;
import com.ngocnb20.travel.model.dto.resp.PageRespDto;
import com.ngocnb20.travel.model.dto.resp.PlaceRespDto;
import com.ngocnb20.travel.model.entity.Place;
import com.ngocnb20.travel.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/places",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class PlaceController extends BaseController {

    @Autowired
    PlaceService placeService;


    @GetMapping()
    public ResponseEntity<BaseRespDto> getAllPlace(){
        List<Place> places = placeService.findAll();
        List<PlaceRespDto> placeRespDtos = places.stream().map(p->placeService.convertDto(p))
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS, placeRespDtos)
        );
    }


    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getBlogByPlaceId(@PathVariable Long id){
        Optional<Place> place = placeService.findPlaceById(id);
        if(place.isPresent()){
            PlaceRespDto placeRespDto =placeService.convertDto(place.get());
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS, placeRespDto)
            );
        }else
        {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }


    @GetMapping(path = "/top-six")
    public ResponseEntity<BaseRespDto> getTopPlaceByNumberView(){
        List<Place> places= placeService.getTopByNumberView();
        List<PlaceRespDto> placeRespDtos = places.stream().map(p->placeService.convertDto(p)).collect(Collectors.toList());
        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,placeRespDtos)
        );

    }

    @GetMapping({"/page/{page}"})
    public ResponseEntity<BaseRespDto> getAllBlogByPage(@PathVariable("page") String page,@RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage=Integer.parseInt(page)-1;
        Page<Place> places = placeService.getDataByPage(numberPage,"",search);
        int totalPage = places.getTotalPages();
        System.out.println("total item"+places.getTotalElements());
        System.out.println("total page"+places.getTotalPages());
        List<PlaceRespDto> placeRespDtos = places.getContent().stream()
                                                .map(p->placeService.convertDto(p))
                                                .collect(Collectors.toList());
        PageRespDto<PlaceRespDto> pageData=new PageRespDto(totalPage,placeRespDtos);

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }


}
