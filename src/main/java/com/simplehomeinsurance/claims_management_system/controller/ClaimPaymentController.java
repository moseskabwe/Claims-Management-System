package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.ClaimPaymentService;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.UserService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Controller
@RequestMapping("dashboard")
public class ClaimPaymentController {
	
	@Autowired
	private ClaimPaymentService claimPaymentService;
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/showPayments")
	public String showPayments(Model theModel,  Principal principal) {
		
		List<ClaimPayment> claimPaymentList = claimPaymentService.getClaimPaymentList();
		
		User user = userService.getUserbyUsername(principal.getName());
		
		theModel.addAttribute("claimPayments", claimPaymentList);
		
		theModel.addAttribute("user", user);
		
		return "payments";
		
	}
	
//	@GetMapping("/showPayments/showPaymentDetail")
//	public String showPaymentDetail(@ModelAttribute("paymentNumber") int paymentNumber, Model theModel) {
//		
//		ClaimPayment claimPayment = claimPaymentService.getClaimPayment(paymentNumber);
//		
//		theModel.addAttribute("claimPayment", claimPayment);
//		
//		return "";
//		
//	}
	
	@GetMapping("/finaliseClaim")
	public String finaliseClaim(@ModelAttribute("claimNumber") String claimNumber, 
								Model model) {
		
		Claim claim = claimService.getClaim(claimNumber);
		
		ClaimPayment payment = new ClaimPayment();
		
		model.addAttribute("payment", payment);
		model.addAttribute("claim", claim);
		
		return "finalise-claim";
	}
	
	@PostMapping("/finaliseClaim")
	public String makePayment(@ModelAttribute("claimNumber") String claimNumber, 
								@Valid @ModelAttribute("payment") ClaimPayment payment,
								BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			Claim claim = claimService.getClaim(claimNumber);
			
			model.addAttribute("payment", payment);
			model.addAttribute("claim", claim);
			
			return "finalise-claim";
			
		} else {
			
			Claim claim = claimService.getClaim(claimNumber);
			
			claim.addPayment(payment);
			
			claim.setStatus("Finalised");
			
			claimService.updateClaim(claim);
			
			Date date = new Date();  
			
			payment.setClaim(claim);
			
			payment.setPaymentDate(DateUtils.formatDate(date));
			
			claimPaymentService.saveClaimPayment(payment);
			
			return "redirect:/dashboard/listClaims/showClaimDetails";
			
		}
	}
}
