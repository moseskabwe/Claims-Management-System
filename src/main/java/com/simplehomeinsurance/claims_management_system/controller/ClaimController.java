package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.PolicyService;

@Controller
@RequestMapping("dashboard/")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private PolicyHolderService policyHolderService;
	
	@Autowired
	private PolicyService policyService;
	
	@GetMapping("listClaims")
	public String showClaims(Model theModel) {
		
		List<Claim> theClaims = claimService.getClaimsList();
		
		theModel.addAttribute("claimsList", theClaims);
		
		return "claims";
	}
	
	@GetMapping("listClaims/showClaimDetails")
	public String showClaimDetails(@ModelAttribute("claimNumber") String claimNumber,
									Model theModel) {
		
		Claim theClaim = claimService.getClaim(claimNumber);
		
		
		theModel.addAttribute("claim", theClaim);
		
		return "claim-details";
	}
	
	@GetMapping("/listClaims/editClaim")
	public String editClaim(@ModelAttribute("claimNumber") String claimNumber,
									Model theModel) {
		
		Claim theClaim = claimService.getClaim(claimNumber);
		
		theModel.addAttribute("claim", theClaim);
		
		return "edit-claim";
	}
	
	@GetMapping("/fileClaim")
	public String fileClaim() {

		return "search-policyholders";
	}
	
	@GetMapping("addClaimDetails/{policyholder.policyHolderNumber}/{policy.policyNumber}")
	public String addClaimDetails(@PathVariable("policyholder.policyHolderNumber")
									String policyholderNumber,
									@PathVariable("policy.policyNumber")
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
	
	@PostMapping("addClaimDetails/{policyholder.policyHolderNumber}/{policy.policyNumber}")
	public String saveClaim(@PathVariable("policyholder.policyHolderNumber") String policyHoldeNumber,
							@PathVariable("policy.policyNumber") String policyNumber,
							@ModelAttribute("claim") Claim claim) {
		
		PolicyHolder policyHolder = policyHolderService.getPolicyHolder(policyHoldeNumber);
		
		Policy policy = policyService.getPolicy(policyNumber);
		
		claim.setPolicyHolder(policyHolder);
		claim.setPolicy(policy);
		
		System.out.println(claim.toString());

		claimService.saveClaim(claim);

		return "redirect:/dashboard";
	}
}
