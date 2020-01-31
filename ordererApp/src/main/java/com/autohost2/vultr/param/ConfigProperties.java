package com.autohost2.vultr.param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
/**
 * Permet de récupérer des options dans le fichier application.properties
 * @author mehdi
 *
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "vultr")
public class ConfigProperties {
     
	
    private String token;
    public String getToken() {
		return token;
	}
    public void setToken(String token) {
		this.token = token;
	}
    
    private String baseurl;
    public String getBaseurl() {
		return baseurl;
	}
    public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
    
    private String ansible;
    public String getAnsible() {
		return ansible;
	}
    public void setAnsible(String ansible) {
		this.ansible = ansible;
	}
    
 
}