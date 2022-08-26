package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;
import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.PolicyService;
import com.simplehomeinsurance.claims_management_system.service.UserService;

@Controller
@RequestMapping("dashboard/")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private PolicyHolderService policyHolderService;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("listClaims")
	public String showClaims(Model theModel) {
		
		List<Claim> theClaims = claimService.getClaimsList();
		
		theModel.addAttribute("claimsList", theClaims);
		
		return "claims";
	}
	
	@GetMapping("myClaims")
	public String showMyClaims(HttpServletRequest request, Model model, Principal principal) {
		
		User user = userService.getUserbyUsername(principal.getName());
		
		if (request.isUserInRole("ROLE_ADJUSTER")) {
			
			List<Claim> myClaims = user.getClaims();
			
			model.addAttribute("myClaims", myClaims);
		}
		
		return "my-claims";
	}
	
	@GetMapping("listClaims/showClaimDetails")
	public String showClaimDetails(@ModelAttribute("claimNumber") String claimNumber,
									HttpServletRequest request, Model theModel,
									Principal principal) {
		
		Claim theClaim = claimService.getClaim(claimNumber);
	
		if (request.isUserInRole("ROLE_ADJUSTER")) {
			
			if (theClaim.getStatus().equalsIgnoreCase("First Notice")) {
				
				User user = userService.getUserbyUsername(principal.getName());
				
				theClaim.setStatus("In Progress");
				theClaim.setAdjuster(user);
				
				claimService.updateClaim(theClaim);
			}
		}
		
		
		DeclinedClaim declinedClaim = theClaim.getDeclinedClaim();
		
		List<ClaimPayment> paymentsList = theClaim.getPayments();
		
		theModel.addAttribute("claim", theClaim);
		
		theModel.addAttribute("declinedClaim", declinedClaim);
		
		theModel.addAttribute("paymentsList", paymentsList);
		
		return "claim-details";
	}
	
	@GetMapping("addClaimDetails")
	public String addClaimDetails(@ModelAttribute("policyHolderNumber")
									String policyholderNumber,
									@ModelAttribute("policyNumber")
									String policyNumber,
									Model theModel) {
		
		PolicyHolder policyHolder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		Policy policy = policyService.getPolicy(policyNumber);
		
		Claim claim = new Claim();
		
		policyHolder.addClaim(claim);
		
		theModel.addAttribute("policyHolder", policyHolder);
		
		theModel.addAttribute("policy", policy);

		theModel.addAttribute("claim", claim);
		
		return "file-claim-form";
	}
	
	@PostMapping("addClaimDetails")
	public String saveClaim(@ModelAttribute("policyHolderNumber") String policyHoldeNumber,
							@ModelAttribute("policyNumber") String policyNumber,
							@ModelAttribute("claim") Claim claim) {
		
		PolicyHolder policyHolder = policyHolderService.getPolicyHolder(policyHoldeNumber);
		
		Policy policy = policyService.getPolicy(policyNumber);
		
		claim.setPolicyHolder(policyHolder);
		claim.setPolicy(policy);

		claimService.saveClaim(claim);

		return "redirect:/dashboard";
	}
}
