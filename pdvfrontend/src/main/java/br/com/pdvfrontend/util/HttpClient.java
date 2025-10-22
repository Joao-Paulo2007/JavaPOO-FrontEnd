package br.com.pdvfrontend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HttpClient() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    // GET genérico
    public <T> T get(String url, Class<T> responseType) {
        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Erro ao fazer GET em: " + url);
            e.printStackTrace();
            return null;
        }
    }

    // POST genérico
    public <T> T post(String url, Object requestBody, Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<T> response = restTemplate.postForEntity(url, entity, responseType);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Erro ao fazer POST em: " + url);
            e.printStackTrace();
            return null;
        }
    }

    // PUT genérico
    public void put(String url, Object requestBody) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);
            restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
        } catch (Exception e) {
            System.err.println("Erro ao fazer PUT em: " + url);
            e.printStackTrace();
        }
    }

    // DELETE genérico
    public void delete(String url) {
        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            System.err.println("Erro ao fazer DELETE em: " + url);
            e.printStackTrace();
        }
    }
}