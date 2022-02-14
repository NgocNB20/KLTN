package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Hotel;
import com.ngocnb20.travel.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant,Long>, JpaSpecificationExecutor<Restaurant> {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
    @Query(value = "SELECT TOP(10) * FROM restaurants WHERE id NOT IN(:id) ORDER BY number_view Desc ",nativeQuery = true)
    List<Restaurant> getRestaurantTop(@Param("id") Long id);
}
