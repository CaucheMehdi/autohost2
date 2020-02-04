package com.autohost.demo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.autohost.demo.entity.Customer;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {

    @RestResource(rel = "byTrackingId", path = "byTrackingId")
    Customer findByTrackingId(@Param("resTrackId") String trackingId);

    @RestResource(rel = "existByTrackingId", path = "existByTrackingId")
    Boolean trackingId(String trackingId);

    @Override
    Customer save(Customer r);

    @RestResource(rel = "byPhone", path = "byPhone")
    Customer findByPhone(@Param("phone") String phone);

    @RestResource(rel = "byEmail", path = "byEmail")
    Customer findByEmail(@Param("email") String email);

    @RestResource(rel = "delete", path = "delete")
    void deleteByTrackingId(@Param("email") String email);

}
