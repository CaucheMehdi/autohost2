package com.autohost.demo.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.autohost.demo.entity.Customer;

@Component
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {

    @Override
    List<Customer> findAll();

    Customer findByTrackingId(@Param("resTrackId") String trackingId);

    Boolean trackingId(String trackingId);

    @Override
    Customer save(Customer r);

    Customer findByPhone(@Param("phone") String phone);

    Customer findByEmail(@Param("email") String email);

    void deleteByTrackingId(@Param("email") String email);

}
