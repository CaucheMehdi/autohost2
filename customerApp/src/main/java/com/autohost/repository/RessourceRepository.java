package com.autohost.repository;

import org.springframework.data.repository.CrudRepository;

import com.autohost.customer.entity.Ressource;

public interface RessourceRepository extends CrudRepository<Ressource, Long> {

    Ressource findByTrackingId(String trackingId);

}
