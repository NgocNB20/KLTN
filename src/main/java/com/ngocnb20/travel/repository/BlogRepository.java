package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.dto.resp.MenuRespDto;
import com.ngocnb20.travel.model.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends BaseRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    @Query(value = "SELECT TOP(10) * FROM blogs WHERE id NOT IN(:id) ORDER BY date_blog Desc ",nativeQuery = true)
    List<Blog> getBlogTop(@Param("id") Long id);

}


