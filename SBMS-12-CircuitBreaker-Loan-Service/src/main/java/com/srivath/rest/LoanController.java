package com.srivath.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivath.binding.Rate;
import com.srivath.service.LoanService;

@RestController
public class LoanController {

	@Autowired
	private LoanService service;

	@GetMapping("/loan")
	public Rate getRateByLoanType(@RequestParam("type") String loanType) {
		return service.getRate(loanType);
	}
}
