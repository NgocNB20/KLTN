package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Agency;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AgencyRepository extends BaseRepository<Agency,Long>, JpaSpecificationExecutor<Agency> {
    List<Agency> findAll();
    Optional<Agency> findById(Long id);
}
