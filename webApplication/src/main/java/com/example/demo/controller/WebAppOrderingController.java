package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entityDTO.dto.CustomerDTO;
import entityDTO.dto.RessourceDTO;
import entityDTO.dto.UrlEndpoint;

// try change
@Controller
public class WebAppOrderingController {

    @Autowired
    private ObjectMapper        mapper;
    private static final String INDEX  = "index";
    private static final Logger logger = LoggerFactory.getLogger(WebAppOrderingController.class);

    @PostMapping("/order")
    public String formPostOrdering(@ModelAttribute RessourceDTO order, Model model) throws ClientProtocolException, IOException {
        String response = "NOT_ACCEPTED";
        if (order != null && order.getApplication() != null && order.getClientTid() != null && order.getOs() != null && order.getPlace() != null
                        && order.getProvider() != null && order.getSize() != null) {
            RessourceDTO neworder = restRequest(order, UrlEndpoint.CUSTOMER_POST_ORDER);
        }
        // adding all data for the view
        addAttributeToView(model, response);
        // save order to internal db ??
        // redirect to other controller on main page
        return INDEX;
    }

    /*
     * ------------------------------------------------COMMON
     * METHOD----------------------------------------------------------------------- ----------------
     */
    private RessourceDTO restRequest(@ModelAttribute RessourceDTO form, String url) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // setting headers to send JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        // map the formular in a Json String Value
        String jsonForm = mapper.writeValueAsString(form);
        // request will receive a String in return, could be an object defined
        HttpEntity<String> request = new HttpEntity<>(jsonForm, headers);
        return restTemplate.postForObject(url, request, RessourceDTO.class);

    }

    /**
     * Common method to add attribute for the model of View listClient.html
     *
     * @param model
     * @param message Message to be displayed
     * @throws ClientProtocolException
     * @throws IOException
     */
    private Model addAttributeToView(Model model, String message) throws ClientProtocolException, IOException {
        // TODO Auto-generated method stub
        // adding Formular for the view listClient.html
        model.addAttribute("form", new CustomerDTO());
        model.addAttribute("formDelete", new CustomerDTO());
        model.addAttribute("formOrdering", new RessourceDTO());

        // adding Customer List for the view listClient.html
        model.addAttribute("listCustomer", getAllCustomer());
        // adding message to shown for the view listClient.html
        model.addAttribute("message", message);
        return model;
    }

    /**
     * get All client form requesting endpoint /customer/getall
     *
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private List<Customer> getAllCustomer() throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:8889/customer/getall"));
        String json = EntityUtils.toString(response.getEntity());
        logger.info(json);
        return Arrays.asList(mapper.readValue(json, Customer[].class));
    }

}
