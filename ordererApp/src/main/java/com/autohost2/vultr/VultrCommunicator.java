package com.autohost2.vultr;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.autohost2.vultr.param.ConfigProperties;
import com.fasterxml.jackson.core.JsonFactory;

/**
 * Send a json to vultr REST API
 * @author mehdi
 *
 */
@Component
public class VultrCommunicator {
	
	@Autowired
	private ConfigProperties configProperties;
		
	private HttpHeaders headers;
	private HttpEntity<?>  request;
	private String url;
	private RestTemplate restTemplate;

	
	
	public VultrCommunicator() {
		super();
	}
	@PostConstruct
	private void init() {
		this.headers = new HttpHeaders();
	    this.headers.add("API-Key", configProperties.getToken());
	    this.request = new HttpEntity<String>(null, this.headers);
	    this.restTemplate = new RestTemplate();

	}
	/**
	 * 
	 * @param vultrCommand
	 * @return
	 */
	public ResponseEntity<String>  get(String vultrCommand){
		// recupere l'url de base dans le fichier properties et ajoute le point d'appel passé en paramètre
		this.url = configProperties.getBaseurl() + vultrCommand;
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET ,this.request, String.class);
		System.out.println("url: " + url);
		return response;
	}	
	/**
	 * methode générique qui poste une map de donnée à une URL
	 * @param urlCommand
	 * @return
	 * @throws JSONException 
	 */
	public ResponseEntity<String>  post(JSONObject jsonObject ) throws JSONException{
		// recupere le json object et construit la requete
		
		String url = (String) jsonObject.get("url");
		jsonObject.remove("url");
		this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// building map to post a form
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("DCID", jsonObject.getString("DCID"));
		map.add("VPSPLANID", jsonObject.getString("VPSPLANID"));
		map.add("OSID", jsonObject.getString("OSID"));
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		
		
		
		return response;
		
		
		
	}
	

}
