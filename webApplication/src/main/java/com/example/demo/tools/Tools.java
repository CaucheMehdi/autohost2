package com.example.demo.tools;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import entityDTO.dto.CustomerDTO;
import entityDTO.dto.RessourceDTO;

@Component
public class Tools {

    public Model addAttributeToView(Model model, String message) throws ClientProtocolException, IOException {
        // TODO Auto-generated method stub
        // adding Formular for the view listClient.html
        model.addAttribute("form", new CustomerDTO());
        model.addAttribute("formDelete", new CustomerDTO());
        model.addAttribute("formOrdering", new RessourceDTO());

        // adding Customer List for the view listClient.html
        // model.addAttribute("listCustomer", getAllCustomer());
        // adding message to shown for the view listClient.html
        model.addAttribute("message", message);
        return model;
    }

}
