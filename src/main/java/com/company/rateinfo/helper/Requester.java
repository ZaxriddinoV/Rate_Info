package com.company.rateinfo.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
@Component
@RequiredArgsConstructor
public class Requester {

    private final RestTemplate restTemplate;

    public <B> B exchange(URI uri, HttpMethod httpMethod, HttpEntity<?> httpEntity, ParameterizedTypeReference<B> parameterizedTypeReference) {

        B result = null;

        try {
            ResponseEntity<B> exchange = restTemplate.exchange(uri, httpMethod, httpEntity, parameterizedTypeReference);
            System.out.println("Response: " + exchange);
            result = Objects.requireNonNull(exchange.getBody());


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
