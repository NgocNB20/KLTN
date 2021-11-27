package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.CategoryPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPlaceRepository extends JpaRepository<CategoryPlace,Long> {
}
