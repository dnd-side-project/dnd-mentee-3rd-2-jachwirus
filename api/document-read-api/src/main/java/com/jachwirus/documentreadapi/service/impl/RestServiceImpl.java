package com.jachwirus.documentreadapi.service.impl;

import com.jachwirus.documentreadapi.service.RestService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl implements RestService {
    private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getPlainText(String url) {
        String responseData = this.restTemplate.getForObject(url, String.class);
        return responseData;
    }
}
