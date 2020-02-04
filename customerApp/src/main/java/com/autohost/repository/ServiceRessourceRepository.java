package com.autohost.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import entityDTO.db.Ressource;

@Service
public class ServiceRessourceRepository {

    public boolean save(Ressource c) {
        // TODO Auto-generated method stub
        String url = "http://10.0.1.241:9900/customer";
        ResponseEntity<Ressource> r = postRequest(url, c);
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
    private ResponseEntity<Ressource> postRequest(String url, Ressource r) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ressource> res = restTemplate.postForObject(url, r, ResponseEntity.class);
        if (res.getStatusCode() != HttpStatus.CREATED) {
            // i can't access to reposirory !
            System.out.println("pb acces data");

        } else {
            return res;
        }
        return res;
    }
}
