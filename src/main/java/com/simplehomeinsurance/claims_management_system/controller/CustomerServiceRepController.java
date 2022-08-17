package com.simplehomeinsurance.claims_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.CustomerServiceRep;
import com.simplehomeinsurance.claims_management_system.service.CustomerServiceRepService;


@Controller
@RequestMapping("dashboard/csr")
public class CustomerServiceRepController {
	
	@Autowired
	private CustomerServiceRepService customerServiceRepService;
	
	@GetMapping("/showCsrDetails")
	public String showPaymentDetail(@ModelAttribute("customerServiceRepNumber") int customerServiceRepNumber, Model theModel) {
		
		CustomerServiceRep customerServiceRep = customerServiceRepService.getCustomerServiceRep(customerServiceRepNumber);
		
		theModel.addAttribute("customerServiceRep", customerServiceRep);
		
		return "csr-details";
		
	}
	
	@PostMapping("/showCsrDetails/editCsrDetails")
	public String editCsrDetails(@ModelAttribute("customerServiceRep") CustomerServiceRep customerServiceRep) {
		
		customerServiceRepService.saveCustomerServiceRep(customerServiceRep);
		
		return "change-csr-details";
	}
}
