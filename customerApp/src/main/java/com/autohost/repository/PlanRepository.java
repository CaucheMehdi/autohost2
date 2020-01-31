package com.autohost.repository;

import org.springframework.data.repository.CrudRepository;

import com.autohost.customer.entity.Ressource;

public interface PlanRepository extends CrudRepository<Ressource, Long> {

}
