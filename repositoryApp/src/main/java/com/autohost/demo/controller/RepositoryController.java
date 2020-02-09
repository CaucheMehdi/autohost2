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
import entityDTO.dto.MessageDto;
import entityDTO.dto.UrlEndpoint;

/**
 * Rest conTroller for managing CUSTOMER entity
 * 
 * @author mehdi
 *
 */
@RestController
public class RepositoryController {
    private static final String REST_REPOSITORY = "RestDAO Controller CUSTOMER_entity";
	@Autowired
    CustomerRepo customerRepository;
		
	/**
	 * CREATE CUSTOMER
	 * @param msgreceived
	 * @return
	 */
	@PostMapping(UrlEndpoint.REST_CUSTOMER_SAVE_MAPPING)
    public ResponseEntity<MessageDto> saveCustomer(MessageDto msgreceived) {
        Customer customer = customerRepository.save(customerDtoToCustomer((CustomerDTO) msgreceived.getObject()));
        MessageDto msg = new MessageDto();
        msg.setFrom(REST_REPOSITORY);
        CustomerDTO customerDtoToBeSend = customerToCustomerDto(customer);
        msg.setObject(customerDtoToBeSend);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

	/**
	 * GET ALL CUSTOMER
	 * @return
	 */
    @GetMapping(UrlEndpoint.REST_CUSTOMER_GET_ALL_MAPPING)
    public ResponseEntity<MessageDto> getAllCustomer() {
        List<Customer> listCustomer = customerRepository.findAll();
        List<CustomerDTO> listCustomerDto = new ArrayList<>();
        ListCustomerDto l = new ListCustomerDto();
        for (Customer c : listCustomer) {
            listCustomerDto.add(customerToCustomerDto(c));
        	}
        l.setListCustomerDto(listCustomerDto);
        MessageDto msg = new MessageDto();
        msg.setFrom(REST_REPOSITORY);
        msg.setObject(l); 
        return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    }
    
    
    /**
     * EXIST BY EMAIL ?
     * @param m
     * @return
     */
    @SuppressWarnings("deprecation")
	@PostMapping(UrlEndpoint. REST_CUSTOMER_EXIST_BY_EMAIL_MAPPING)
    public ResponseEntity<MessageDto> existByEmail(MessageDto m) {
        MessageDto msg = new MessageDto();
    	msg.setFrom(REST_REPOSITORY);
        CustomerDTO c = (CustomerDTO) m.getObject();

        if( customerRepository.existsByEmail(c.getEmail())) {
        	msg.setObject(new Boolean(true));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } 
        else {
        	msg.setObject(new Boolean(false));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        }
    }
    
    /**
     * EXIST BY PHONE ?
     * @param m
     * @return
     */
    @SuppressWarnings("deprecation")
	@PostMapping(UrlEndpoint. REST_CUSTOMER_EXIST_BY_PHONE_MAPPING)
    public ResponseEntity<MessageDto> existByPhone(MessageDto m) {
        MessageDto msg = new MessageDto();
    	msg.setFrom(REST_REPOSITORY);
        CustomerDTO c = (CustomerDTO) m.getObject();

        if( customerRepository.existsByPhone(c.getPhone())) {
        	msg.setObject(new Boolean(true));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } 
        else {
        	msg.setObject(new Boolean(false));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        }
    }
    
    /**
     * EXIST BY TRACKING ID ?
     * @param m
     * @return
     */ 
    @SuppressWarnings("deprecation")
	@PostMapping(UrlEndpoint. REST_CUSTOMER_EXIST_BY_TRACKING_ID_MAPPING)
    public ResponseEntity<MessageDto> existByTrackingId(MessageDto m) {
        MessageDto msg = new MessageDto();
    	msg.setFrom(REST_REPOSITORY);
        CustomerDTO c = (CustomerDTO) m.getObject();

        if( customerRepository.existsByTrackingId(c.getTrackingId())) {
        	msg.setObject(new Boolean(true));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        } 
        else {
        	msg.setObject(new Boolean(false));
        	return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
        }
    }
    
    
    
   
    
    
    /**OBJECT DTO CONVERTER**/
    
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
