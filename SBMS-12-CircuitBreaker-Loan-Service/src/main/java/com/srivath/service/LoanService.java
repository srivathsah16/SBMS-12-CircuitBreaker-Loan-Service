package com.srivath.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srivath.binding.Rate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoanService {

	@Autowired
	private RestTemplate restTemplate;
	private final String URL_OF_RATE_RESTAPI = "http://localhost:9090/rate?type=?";
	private final String CB_NAME = "loan-service";

	@CircuitBreaker(name = CB_NAME, fallbackMethod = "fallBackMethod")
	public Rate getRate(String loanType) {
		System.out.println("**** ORIGINAL METHOD TRIGGERED ****");
		// making GET Request to RATE REST API.
		Map<String, String> queryParamsMap = new HashMap<>();
		queryParamsMap.put("type", loanType);
		ResponseEntity<Rate> responseEntity = restTemplate.getForEntity(URL_OF_RATE_RESTAPI, Rate.class,
				queryParamsMap);
		return responseEntity.getBody();
	}

	public Rate fallBackMethod() {
		System.out.println("**** FALLBACK METHOD TRIGGERED ****");
		// some fallback logic...
		return new Rate();
	}
}
