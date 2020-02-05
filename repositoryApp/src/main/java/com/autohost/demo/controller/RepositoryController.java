package com.autohost.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autohost.demo.entity.Customer;
import com.autohost.demo.repo.CustomerRepo;

import entityDTO.dto.CustomerDTO;
import entityDTO.dto.ListCustomerDto;

@RestController
public class RepositoryController {
    @Autowired
    CustomerRepo customerRepository;

    @GetMapping("/customer/all")
    public ResponseEntity<ListCustomerDto> getAllCustomer() {

        List<Customer> listCustomer = customerRepository.findAll();
        List listCustomerDto = new ArrayList();
        ListCustomerDto l = new ListCustomerDto();
        for (Customer c : listCustomer) {
            listCustomerDto.add(customerToCustomerDto(c));
        }
        l.setListCustomerDto(listCustomerDto);
        return new ResponseEntity<>(l, HttpStatus.ACCEPTED);
    }

    @PostMapping("/customer/save")
    public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO c) {
        customerRepository.save(customerDtoToCustomer(c));
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    private CustomerDTO customerToCustomerDto(Customer c) {
        CustomerDTO cdto = new CustomerDTO();
        cdto.setEmail(c.getEmail());
        cdto.setName(c.getName());
        cdto.setPhone(c.getPhone());
        cdto.setPwd(c.getPwd());
        cdto.setSurname(c.getSurname());
        cdto.setTrackingId(c.getTrackingId());
        return cdto;
    }

    private Customer customerDtoToCustomer(CustomerDTO cdto) {
        Customer c = new Customer();
        c.setEmail(cdto.getEmail());
        c.setName(cdto.getName());
        c.setPhone(cdto.getPhone());
        c.setPwd(cdto.getPwd());
        c.setSurname(cdto.getSurname());
        c.setTrackingId(cdto.getTrackingId());
        return c;
    }

}
