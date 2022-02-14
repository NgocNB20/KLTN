package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.*;
import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.model.entity.Hotel;
import com.ngocnb20.travel.model.entity.Restaurant;
import com.ngocnb20.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/hotels",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class HotelController {

    @Autowired
    HotelService hotelService;


    @GetMapping
    public ResponseEntity<BaseRespDto> getAllHotel(){
        List<Hotel> hotels = hotelService.fillAll();
        System.out.println(hotels.size());
        List<HotelRespDto> hotelDtos = hotels.stream()
                .map(h->hotelService.convertDto(h))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,hotelDtos)
        );

    }

    @GetMapping("/page/{page}")
    public ResponseEntity<BaseRespDto> getAllHotelByPage(@PathVariable("page") String page, @RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage  = Integer.parseInt(page)-1;
        Page<Hotel> hotels = hotelService.getDataByPage(numberPage,"",search);
        int totalPage   =   hotels.getTotalPages();
        long totalItem  = hotels.getTotalElements();
        System.out.println("total item"+hotels.getTotalElements());
        System.out.println("total page"+hotels.getTotalPages());

        List<HotelRespDto> hotelDtos  =   hotels.getContent().stream()
                .map(h->hotelService.convertDto(h))
                .collect(Collectors.toList());
        PageRespDto<RestaurantRespDto> pageData=new PageRespDto(totalPage,hotelDtos,totalItem   );

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }

    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getRestaurantById(@PathVariable Long id){
        Optional<Hotel> hotel = hotelService.findHotelById(id);

        if(hotel.isPresent()){
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,hotel.get())
            );
        }else {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }

    @GetMapping(path = "/top/{id}")
    public ResponseEntity<BaseRespDto> getTopBlogNew(@PathVariable Long id){
        List<Hotel> hotels = hotelService.getHotelTop(id);
        List<HotelRespDto> hotelRespDtos=hotels.stream().map(h ->hotelService.convertDto(h)).collect(Collectors.toList());
        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,hotelRespDtos)
        );
    }
}
