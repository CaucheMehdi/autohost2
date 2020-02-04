package com.autohost.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.autohost.demo.entity.Ressource;

@RepositoryRestResource(collectionResourceRel = "ressource", path = "ressource")
public interface RessourceRepo extends PagingAndSortingRepository<Ressource, Long> {

    @RestResource(rel = "byTrackingId", path = "byTrackingId")
    Ressource findByTrackingId(@Param("resTrackId") String trackingId);

    @Override
    Ressource save(Ressource r);

}
