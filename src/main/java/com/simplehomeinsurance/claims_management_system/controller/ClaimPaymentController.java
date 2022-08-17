package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.service.ClaimPaymentService;

@Controller
@RequestMapping("dashboard/payments")
public class ClaimPaymentController {
	
	@Autowired
	private ClaimPaymentService claimPaymentService;
	
	@GetMapping("/showPayments")
	public String showPayments(Model theModel) {
		
		List<ClaimPayment> claimPaymentList = claimPaymentService.getClaimPaymentList();
		
		theModel.addAttribute("claimPayments", claimPaymentList);
		
		return "payments";
		
	}
	
	@GetMapping("/showPayments/showPaymentDetail")
	public String showPaymentDetail(@ModelAttribute("paymentNumber") int paymentNumber, Model theModel) {
		
		ClaimPayment claimPayment = claimPaymentService.getClaimPayment(paymentNumber);
		
		theModel.addAttribute("claimPayment", claimPayment);
		
		return "dashboard";
		
	}
}
