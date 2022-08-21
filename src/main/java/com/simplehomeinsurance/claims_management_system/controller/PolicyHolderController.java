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

import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;

@Controller
public class PolicyHolderController {

	@Autowired
	private PolicyHolderService policyHolderService;
	
	@RequestMapping("dashboard/searchPolicyholders")
	public String searchPolicyholders(@RequestParam("searchTerm") String searchTerm,
										Model theModel) {
		
		List<PolicyHolder> policyHolderList = policyHolderService.searchPolicyHolders(searchTerm);
		
		theModel.addAttribute("policyholderList", policyHolderList);
		
		return "policyholders-results";
	}
	
	@GetMapping("/searchPolicyholders/showPolicyholderDetails")
	public String showPaymentDetail(@ModelAttribute("policyholderNumber") String policyholderNumber, Model theModel) {
		
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		theModel.addAttribute("policyholder", policyholder);
		
		return "policyholder-details";	
	}
	
	@PostMapping("/searchPolicyholders/editPolicyHolderDetails")
	public String editPolicyHolderDetails(@ModelAttribute("policyholder") PolicyHolder policyholder) {
		
		policyHolderService.savePolicyHolder(policyholder);
		
		return "policyholders-results";
	}
}
