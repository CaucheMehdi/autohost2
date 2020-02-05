package com.autohost.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.autohost.demo.entity.Ressource;

public interface RessourceRepo extends PagingAndSortingRepository<Ressource, Long> {

    Ressource findByTrackingId(@Param("resTrackId") String trackingId);

    @Override
    Ressource save(Ressource r);

}
