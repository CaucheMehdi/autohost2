package com.autohost.customer.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import entityDTO.db.Customer;
import entityDTO.db.Ressource;
import entityDTO.dto.CustomerDTO;
import entityDTO.dto.RessourceDTO;
import entityDTO.dto.Status;
import entityDTO.dto.UrlEndpoint;
import entityDTO.utils.DtoConverter;

@RestController
public class CustomerController {

    @Autowired
    private ServiceCustomerRepository cusRepo;

    @Autowired
    private ServiceRessourceRepository resRepo;

    @Value("${url.manager}")
    private String              urlBaseManager;
    private static final String MANAGER_GET_RESSOURCE = "/get/ressource";

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    /**
     * REGISTER CUSTOMER
     *
     * @return
     */
    @CrossOrigin(origins = "http://10.244.232.246:4200")
    @PostMapping("/customer/create")
    public String createCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.info("received at /customer/create DTO {}", customerDTO);
        // Check if any value is null
        if (customerDTO.getName() == null || customerDTO.getSurname() == null || customerDTO.getPhone() == null || customerDTO.getEmail() == null) {
            logger.info("At least one mandatory value is null");
            return "NULL_VALUE";
            // check if this email is this customer is already know (by email or phone)
        } else if (customerDTO.getName().isEmpty() || customerDTO.getSurname().isEmpty() || customerDTO.getPhone().isEmpty() || customerDTO.getEmail()
                        .isEmpty()) {
            logger.info("At least one mandatory value is empty");
            return "BLANK_VALUE";
        } else if (cusRepo.existsByEmail(customerDTO.getEmail()) || cusRepo.existsByPhone(customerDTO.getPhone())) {
            logger.info("Customer already existing in db");
            return "CUSTOMER_FOUND_IN_DB";
        } else {
            Customer c = DtoConverter.customerDtoToCustomer(customerDTO);
            // setting tracking number
            c.setTrackingId(RandomStringUtils.randomAlphanumeric(30));
            cusRepo.save(c);
            logger.info("saving new customer : {}", c);
            // return unique identifier of this customer
            return c.getTrackingId();
        }
    }

    @PostMapping("/customer/delete")
    public String deleteCustomer(@RequestBody CustomerDTO c) {
        String message = "ID_IS_NULL";

        if (c != null) {
            if (cusRepo.existByTrackingId(c.getTrackingId())) {
                cusRepo.deleteByTrackerId(c.getTrackingId());
                logger.info("Customer deleted db : {}", c);
                message = "DELETED";
            } else {
                logger.info("Customer not found in db");
                message = "CUSTOMER_NOT_FOUND";
            }
        }
        return message;
    }

    @CrossOrigin(origins = "http://10.244.232.246:4200")
    @GetMapping("/customer/getall")
    public List<CustomerDTO> listCustomer() {
        List<Customer> lc = cusRepo.findAll();
        List<CustomerDTO> lcdto = new ArrayList<>();
        for (Customer c : lc) {
            CustomerDTO cdto = DtoConverter.customerToCustomerDto(c);
            lcdto.add(cdto);
        }
        // renvoi la liste des clientsDTO
        return lcdto;

    }

    /**
     * ORDER RESSOURCES FOR CUSTOMER
     * entry point for FrontEnd to ask a ressource for customer tracker id are used
     * to follow during the process the entity like a token passed from one to
     * another app
     *
     * @param trackerIdClient
     * @param trackerIdPlan
     * @param trackerIdCustomer
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    @PostMapping("/customer/post/ressource")
    public ResponseEntity<RessourceDTO> askPlanForCustomer(@RequestBody RessourceDTO res) throws ClientProtocolException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // verify if user exist
        Customer c = cusRepo.findByTrackingId(res.getClientTid());
        // verify if plan exist
        if (c != null && res != null) {
            // Génère un code de suivi (trackingId) et sauvegarde la commande du client
            res.setTrackingId(RandomStringUtils.random(30));
            res.setEvents(new HashMap<LocalTime, String>());
            res.getEvents().put(LocalTime.now(), UrlEndpoint.CUSTOMER_POST_ORDER);
            res.setStatus(Status.PENDING);
            Ressource r = DtoConverter.ressourceDtoToRessource(res);
            resRepo.save(r);

            // envoie la commande au manager
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<RessourceDTO> entity = new HttpEntity<>(res, headers);
            // renvoie au client l'entité avec un status à PENDING
            logger.info("Sending to manager, request for ressource : {}", res);

            return restTemplate.postForEntity(UrlEndpoint.MANAGER_GET_RESSOURCE, entity, RessourceDTO.class);

        }
        // renvoie une entité vide avec un statut à FAILURE
        logger.info("Customer or Ressource is null");
        RessourceDTO resFailed = new RessourceDTO();
        resFailed.setStatus(Status.FAILURE);
        return new ResponseEntity<>(resFailed, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/customer/receive/order")
    public String receivePlanForCustomer(@RequestParam RessourceDTO res) {
        if (res != null || res.getTrackingId() != null || res.getIp() != null) {
            if (!res.getTrackingId().isEmpty() && !res.getIp().isEmpty()) {
                logger.info("received ressource from manager : {}", res);
                Ressource r = resRepo.findByTrackingId(res.getTrackingId());
                r.setIp(res.getIp());
                resRepo.save(r);
            }
        }
        return "OK";
    }

    /**
     * Transform a simple DTO order in a useable Order to be processed
     * @param orderP
     * @return
     */
    private void saveRessoure(RessourceDTO res) {

    }

    /**
     * Send a request to the manager to get a ressource
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private CloseableHttpResponse requestRessourceToManager(List<NameValuePair> params) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlBaseManager + MANAGER_GET_RESSOURCE);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        return client.execute(httpPost);
    }

}
