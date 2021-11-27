package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.model.entity.CategoryPlace;
import com.ngocnb20.travel.model.entity.Place;
import com.ngocnb20.travel.repository.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ngocnb20.travel.repository.CategoryPlaceRepository;
import com.ngocnb20.travel.repository.PlaceRepository;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
   @Autowired
   PlaceService placeService;
    @Autowired
    CategoryPlaceRepository repository;
    @GetMapping("/home")
    public Place save(){
        Set<Place> places=new HashSet<>();
        Place place1=new Place("N1");
        Place place2=new Place("N2");
        places.add(place1);
        places.add(place2);
        Set<CategoryPlace> categoryPlaces=new HashSet<>();
        CategoryPlace categoryPlace=new CategoryPlace("P1");
        CategoryPlace categoryPlace1=new CategoryPlace("P2");
        categoryPlaces.add(categoryPlace1);
        categoryPlaces.add(categoryPlace);
        place1.setCategoryPlaces(categoryPlaces);
        place2.setCategoryPlaces(categoryPlaces);
        placeService.savePlace(place2);
        return placeService.savePlace(place1);
    }
}
