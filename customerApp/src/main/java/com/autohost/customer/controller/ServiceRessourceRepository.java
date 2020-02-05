package com.autohost.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entityDTO.dto.RessourceDTO;
import entityDTO.dto.UrlEndpoint;

@Service
public class ServiceRessourceRepository {

    public boolean save(RessourceDTO c) {
        String url = "http://10.0.1.241:9900/customer";
        ResponseEntity<RessourceDTO> r = postRequest(url, c);
        if (r.getBody() == null) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Send a POST rest request
     * @param url
     * @param parameters
     * @return
     */
    private ResponseEntity<RessourceDTO> postRequest(String url, RessourceDTO r) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RessourceDTO> res = restTemplate.postForObject(url, r, ResponseEntity.class);
        if (res.getStatusCode() != HttpStatus.CREATED) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return res;
        }
        return res;
    }

    public RessourceDTO findByTrackingId(String clientTid) {
        String url = UrlEndpoint.REST_REPO_RESSOURCE + " /search/byTrackingId?resTrackId=" + clientTid;
        return (RessourceDTO) getRequest(url).getBody();
    }

    private <T> ResponseEntity getRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RessourceDTO> r = restTemplate.getForEntity(url, RessourceDTO.class);
        if (r.getStatusCode() != HttpStatus.OK) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return r;
        }
        return r;
    }

}
