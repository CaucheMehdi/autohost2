package com.autohost.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entityDTO.dto.CustomerDTO;
import entityDTO.dto.ListCustomerDto;
import entityDTO.dto.UrlEndpoint;

@Service
@Component
public class ServiceCustomerRepository {

    public boolean existsByEmail(String email) {
        String url = UrlEndpoint.REST_CUSTOMER_SEARCH_BY_EMAIL_ENDPOINT + email;
        ResponseEntity<CustomerDTO> response = getRequest(url);
        if (response.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existsByPhone(String phone) {
        String url = REST_URL_CUSTOMER + "/search/byPhone?phone=" + phone;
        ResponseEntity<CustomerDTO> response = getRequest(url);
        if (response.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existByTrackingId(String trackingId) {
        String url = REST_URL_CUSTOMER + "/search/existByTrackingId?trackingId=" + trackingId;
        ResponseEntity r = getRequest(url);
        if (r.getBody() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean save(CustomerDTO c) {
        // TODO Auto-generated method stub
        String url = REST_URL_CUSTOMER + "/save";
        ResponseEntity<CustomerDTO> r = postRequest(url, c);
        if (r.getBody() == null) {
            return false;
        } else {
            return true;
        }

    }

    public ListCustomerDto findAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(REST_URL_CUSTOMER + "/all", ListCustomerDto.class).getBody();

    }

    public void deleteByTrackerId(String trackingId) {
        String url = UrlEndpoint.REST_REPO_ROOT + "/customer/delete";
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
        ResponseEntity<CustomerDTO> r = restTemplate.getForEntity(url, CustomerDTO.class);
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
    private ResponseEntity<CustomerDTO> postRequest(String url, CustomerDTO c) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDTO> r = restTemplate.postForObject(url, c, ResponseEntity.class);
        if (r.getStatusCode() != HttpStatus.CREATED) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return r;
        }
        return r;
    }

    public CustomerDTO findByTrackingId(String clientTid) {
        String url = REST_URL_CUSTOMER + " /search/byTrackingId?resTrackId=" + clientTid;
        return (CustomerDTO) getRequest(url).getBody();
    }

}
