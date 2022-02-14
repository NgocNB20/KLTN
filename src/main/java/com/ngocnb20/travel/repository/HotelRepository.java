package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends BaseRepository<Hotel,Long>, JpaSpecificationExecutor<Hotel> {

    List<Hotel> findAll();
    Optional<Hotel> findById(Long id);
    @Query(value = "SELECT TOP(10) * FROM hotels WHERE id NOT IN(:id) ORDER BY rate Desc ",nativeQuery = true)
    List<Hotel> getHotelTop(@Param("id") Long id);
}
