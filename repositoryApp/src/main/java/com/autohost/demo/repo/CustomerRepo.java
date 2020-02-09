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
    Boolean trackingId(String trackingId);

    @Override
    Customer save(Customer r);
    void deleteByTrackingId(@Param("email") String email);

    Customer findByPhone(@Param("phone") String phone);
    Customer findByTrackingId(@Param("resTrackId") String trackingId);
    Customer findByEmail(@Param("email") String email);
   
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
    Boolean existsByTrackingId(String trackingId);

}
