package com.autohost.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autohost.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    Customer findByTrackerId(String trackerId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByTrackerId(String trackingId);

    void deleteByTrackerId(String trackingId);

}
