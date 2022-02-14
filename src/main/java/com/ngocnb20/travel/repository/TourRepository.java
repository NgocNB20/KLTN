package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.model.entity.Place;
import com.ngocnb20.travel.model.entity.Tour;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends BaseRepository<Tour,Long>, JpaSpecificationExecutor<Tour> {
    List<Tour> findAll();
    Optional<Tour> findById(Long id);

}
