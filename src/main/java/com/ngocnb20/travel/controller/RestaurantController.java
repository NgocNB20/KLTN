package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.BaseRespDto;
import com.ngocnb20.travel.model.dto.resp.HotelRespDto;
import com.ngocnb20.travel.model.dto.resp.PageRespDto;
import com.ngocnb20.travel.model.dto.resp.RestaurantRespDto;
import com.ngocnb20.travel.model.entity.Hotel;
import com.ngocnb20.travel.model.entity.Restaurant;
import com.ngocnb20.travel.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/restaurants",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<BaseRespDto> getAllRestaurant(){
        List<Restaurant> restaurants = restaurantService.findAll();
        System.out.println(restaurants.size());
        List<RestaurantRespDto> restaurantDtos = restaurants.stream()
                                                            .map(r->restaurantService.ConvertDto(r))
                                                            .collect(Collectors.toList());

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,restaurants)
        );

    }

    @GetMapping("/page/{page}")
    public ResponseEntity<BaseRespDto> getAllBlogByPage(@PathVariable("page") String page, @RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage  = Integer.parseInt(page)-1;
        Page<Restaurant> restaurants = restaurantService.getDataByPage(numberPage,"",search);
        int totalPage   =   restaurants.getTotalPages();
        long totalItem   =   restaurants.getTotalElements();
        System.out.println("total item"+restaurants.getTotalElements());
        System.out.println("total page"+restaurants.getTotalPages());

        List<RestaurantRespDto> restaurantRespDtos  =   restaurants.getContent().stream()
                .map(r->restaurantService.ConvertDto(r))
                .collect(Collectors.toList());
        PageRespDto<RestaurantRespDto> pageData=new PageRespDto(totalPage,restaurantRespDtos,totalItem);

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }

    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getRestaurantById(@PathVariable Long id){
        Optional<Restaurant> restaurant =   restaurantService.findRestaurantById(id);

        if(restaurant.isPresent()){
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,restaurant.get())
            );
        }else {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }

    @GetMapping(path = "/top/{id}")
    public ResponseEntity<BaseRespDto> getTopBlogNew(@PathVariable Long id){
        List<Restaurant> restaurants = restaurantService.getRestaurantTop(id);
        List<RestaurantRespDto> restaurantDtos=restaurants.stream().map(r ->restaurantService.ConvertDto(r)).collect(Collectors.toList());
        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,restaurantDtos)
        );
    }

}
