package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.ParseException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entityDTO.CustomerDTO;
import entityDTO.RessourceDTO;
import entityDTO.UrlEndpoint;

@Controller
public class WebAppCustomerController {

    private static final Logger logger    = LoggerFactory.getLogger(WebAppCustomerController.class);
    private static final String MAIN_VIEW = "index";

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "HelloWorld!");
        return MAIN_VIEW;
    }

    @GetMapping("/allclient")
    public String listClient(Model model) throws ParseException, IOException {
        logger.info("receive request at /allclient from ");
        String message = "Welcome !";
        model = addAttributeToView(model, message);
        return MAIN_VIEW;
    }

    /**
     * Reçoit un formulaire posté par index.html pour enregistrer un nouveau client
     * @param form
     * @param model
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    @PostMapping("/createCustomer")
    public String formSubmitCreateCustomer(@ModelAttribute CustomerDTO form, Model model) throws ClientProtocolException, IOException {

        // Endpoint for create customer (CustomerApp)
        // TODO property should be set in properties file
        logger.info("STARTING PROCESSING FORM");
        String response = restRequest(form, UrlEndpoint.CUSTOMER_CREATE_CUSTOMER);
        // setting the message for the view
        String message = "";
        switch (response) {
        case "CUSTOMER_FOUND_IN_DB":
            message = "Account already registered";
            break;
        }
        // adding all data for the view
        model = addAttributeToView(model, message);
        logger.info("ENDING PROCESSING FORM");
        // return file listClient.html
        return MAIN_VIEW;
    }

    /**
     * Receive Post of formular FormDeleteCustomer
     * @param form
     * @param model
     * @return view listClient.html
     * @throws ClientProtocolException
     * @throws IOException
     */
    @PostMapping("/deleteCustomer")
    public String formSubmitDeleteCustomer(@ModelAttribute CustomerDTO form, Model model) throws ClientProtocolException, IOException {
        logger.info("STARTING PROCESSING FORM");
        String response = restRequest(form, UrlEndpoint.CUSTOMER_DELETE_CUSTOMER);
        model = addAttributeToView(model, response);
        return MAIN_VIEW;
    }

    /**
     * Common method to add attribute for the model of View listClient.html
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
    private List<CustomerDTO> getAllCustomer() throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:8889/customer/getall"));
        String json = EntityUtils.toString(response.getEntity());
        logger.info(json);
        return Arrays.asList(mapper.readValue(json, CustomerDTO[].class));
    }

    /**
     * Send a rest request
     * @param form
     * @param url
     * @return
     * @throws JsonProcessingException
     */
    private String restRequest(@ModelAttribute CustomerDTO form, String url) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // setting headers to send JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        // map the formular in a Json String Value
        String jsonForm = mapper.writeValueAsString(form);
        // request will receive a String in return, could be an object defined
        HttpEntity<String> request = new HttpEntity<>(jsonForm, headers);
        return restTemplate.postForObject(url, request, String.class);

    }

}
