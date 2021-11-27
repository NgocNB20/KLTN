package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Place;
import com.ngocnb20.travel.repository.CategoryPlaceRepository;
import com.ngocnb20.travel.repository.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CategoryPlaceRepository category;

    public Place savePlace(Place place) {
        return this.placeRepository.save(place);

    }
}


