package com.autohost.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entityDTO.db.Customer;

@Service
public class ServiceCustomerRepository {

    public boolean existsByEmail(String email) {
        String url = "http://10.0.1.241:9900/customer/search/byEmail?email=" + email;
        ResponseEntity<Customer> response = getRequest(url);
        if (response.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existsByPhone(String phone) {
        // TODO Auto-generated method stub
        String url = "http://10.0.1.241:9900/customer/search/byPhone?phone=" + phone;
        ResponseEntity<Customer> response = getRequest(url);
        if (response.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existByTrackingId(String trackingId) {
        String url = "http://10.0.1.241:9900/customer/search/existByTrackingId?trackingId=" + trackingId;
        ResponseEntity r = getRequest(url);
        if (r.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean save(Customer c) {
        // TODO Auto-generated method stub
        String url = "http://10.0.1.241:9900/customer";
        ResponseEntity<Customer> r = postRequest(url, c);
        if (r.getBody() == null) {
            return false;
        } else {
            return true;
        }

    }

    public List<Customer> findAll() {
        String url = "http://10.0.1.241:9900/customer";
        RestTemplate restTemplate = new RestTemplate();
        Customer[] customerTab = restTemplate.getForEntity(url, Customer[].class).getBody();
        return Arrays.asList(customerTab);
    }

    public void deleteByTrackerId(String trackingId) {
        String url = "http://10.0.1.241:9900/customer/delete";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, trackingId);
    }

    /**
     * Send a GET rest request
     * @param url
     * @param parameters
     * @return
     */
    private <T> ResponseEntity getRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> r = restTemplate.getForEntity(url, Customer.class);
        if (r.getStatusCode() != HttpStatus.OK) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return r;
        }
        return r;
    }

    /**
     * Send a POST rest request
     * @param url
     * @param parameters
     * @return
     */
    private ResponseEntity<Customer> postRequest(String url, Customer c) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> r = restTemplate.postForObject(url, c, ResponseEntity.class);
        if (r.getStatusCode() != HttpStatus.CREATED) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return r;
        }
        return r;
    }

    public Customer findByTrackingId(String clientTid) {
        String url = " http://10.0.1.241:9900/customer/search/byTrackingId?resTrackId=" + clientTid;
        return (Customer) getRequest(url).getBody();
    }

}
