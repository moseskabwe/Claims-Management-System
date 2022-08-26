package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;

@Controller
public class PolicyHolderController {

	@Autowired
	private PolicyHolderService policyHolderService;
	
	@GetMapping("dashboard/searchPolicyholders")
	public String fileClaim() {

		return "search-policyholders";
	}
	
	@RequestMapping("dashboard/policyholders")
	public String searchPolicyholders(@RequestParam("searchTerm") String searchTerm,
										Model theModel) {
		
		List<PolicyHolder> policyHolderList = policyHolderService.searchPolicyHolders(searchTerm);
		
		theModel.addAttribute("policyholderList", policyHolderList);
		
		return "policyholders-results";
	}
	
	@GetMapping("/policyholders/showPolicyholderDetails")
	public String showPolicyholderDetails(@ModelAttribute("policyHolderNumber") String policyholderNumber, 
											Model theModel) {
		
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		//List<Policy> policyList = policyholder.getPolicies();
		
		List<Claim> claimList = policyholder.getClaims();
		
		theModel.addAttribute("policyholder", policyholder);
		
		//theModel.addAttribute("policyList", policyList);
		
		theModel.addAttribute("claimList", claimList);
		
		return "policyholder-details";
	}
	
	@GetMapping("/policyholders/showPolicyDetails")
	public String showPolicyDetails(@ModelAttribute("policyHolderNumber") String policyholderNumber, Model theModel) {
		
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		List<Policy> policyList = policyholder.getPolicies();
		
		
		theModel.addAttribute("policyholder", policyholder);
		
		theModel.addAttribute("policyList", policyList);
		
		return "policies";
	}
	
	@PostMapping("/policyholders/editPolicyHolderDetails")
	public String editPolicyHolderDetails(@ModelAttribute("policyholder") PolicyHolder policyholder) {
		
		policyHolderService.savePolicyHolder(policyholder);
		
		return "policyholders-results";
	}
}
