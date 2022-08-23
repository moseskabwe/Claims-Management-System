package com.simplehomeinsurance.claims_management_system.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.DeclinedClaimService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Controller
public class DeclinedClaimController {
	
	@Autowired
	private DeclinedClaimService declinedClaimService;
	
	@Autowired
	private ClaimService claimService;
	
	@GetMapping("/declineClaim")
	public String declineClaim(@ModelAttribute("claimNumber") String claimNumber, 
								Model model) {
		
		Claim claim = claimService.getClaim(claimNumber);
		
		DeclinedClaim declinedClaim = new DeclinedClaim();
		
		model.addAttribute("declinedClaim", declinedClaim);
		model.addAttribute("claim", claim);
		
		return "decline-claim";
	}
	
	@PostMapping("/declineClaim")
	public String saveDeclinedClaim(@ModelAttribute("claimNumber") String claimNumber, 
									@ModelAttribute("declinedClaim") DeclinedClaim declinedClaim) {
		
		Claim claim = claimService.getClaim(claimNumber);
		
		claim.setDeclinedClaim(declinedClaim);
		
		claim.setStatus("Declined");
		
		claimService.updateClaim(claim);
		
		Date date = new Date();  
		
		declinedClaim.setClaim(claim);
		
		declinedClaim.setDeclinedDate(DateUtils.formatDate(date));
		
		declinedClaimService.saveDeclinedClaim(declinedClaim);
		
		return "redirect:/dashboard/listClaims/showClaimDetails";
	}
}
