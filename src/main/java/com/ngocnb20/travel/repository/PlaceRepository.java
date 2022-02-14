package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.model.entity.Place;
import com.ngocnb20.travel.model.entity.Tour;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends BaseRepository<Place,Long>, JpaSpecificationExecutor<Place> {
    List<Place> findAll();
    Optional<Place> findById(Long id);
    List<Place> findTop6ByOrderByNumberViewDesc();
}
