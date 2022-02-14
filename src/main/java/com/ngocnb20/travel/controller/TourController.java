package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.BaseRespDto;
import com.ngocnb20.travel.model.dto.resp.PageRespDto;
import com.ngocnb20.travel.model.dto.resp.TourRespDto;
import com.ngocnb20.travel.model.entity.Tour;
import com.ngocnb20.travel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tours",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class TourController {

    @Autowired
    com.ngocnb20.travel.service.TourService tourService;


    @GetMapping()
    public ResponseEntity<BaseRespDto>  getAllTour(){
        List<Tour> tours = tourService.findAll();
        List<TourRespDto> tourRespDtos=tours.stream().map(t->tourService.ConvertDto(t))
                                                      .collect(Collectors.toList());
        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,tourRespDtos)
        );
    }

    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getTourById(@PathVariable Long id){
        Optional<Tour> tour=tourService.findTourById(id);

        if(tour.isPresent()){
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,tour.get())
            );
        }
        else {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<BaseRespDto> getAllBlogByPage(@PathVariable("page") String page,@RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage=Integer.parseInt(page)-1;
        Page<Tour> tours = tourService.getDataByPage(numberPage,"",search);
        int totalPage = tours.getTotalPages();
        System.out.println("total item"+tours.getTotalElements());
        System.out.println("total page"+tours.getTotalPages());

        List<TourRespDto> tourRespDtos = tours.getContent().stream()
                .map(t->tourService.ConvertDto(t))
                .collect(Collectors.toList());
        PageRespDto<TourRespDto> pageData=new PageRespDto(totalPage,tourRespDtos);

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }
}
