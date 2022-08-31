package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
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
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.UserService;

@Controller
public class PolicyHolderController {

	@Autowired
	private PolicyHolderService policyHolderService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("dashboard/searchPolicyholders")
	public String fileClaim(Principal principal, Model model) {
		
		User user = userService.getUserbyUsername(principal.getName());
		
		model.addAttribute("user", user);
		
		return "search-policyholders";
	}
	
	@RequestMapping("dashboard/policyholders")
	public String searchPolicyholders(@RequestParam("searchTerm") String searchTerm,
										Model theModel, Principal principal) {
		
		User user = userService.getUserbyUsername(principal.getName());
		
		List<PolicyHolder> policyHolderList = policyHolderService.searchPolicyHolders(searchTerm);
		
		theModel.addAttribute("policyholderList", policyHolderList);
		theModel.addAttribute("user", user);
		
		return "policyholders-results";
	}
	
	@GetMapping("/policyholders/showPolicyholderDetails")
	public String showPolicyholderDetails(@ModelAttribute("policyHolderNumber") String policyholderNumber, 
											Model theModel,
											Principal principal) {
		
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		User user = userService.getUserbyUsername(principal.getName());
		
		List<Claim> claimList = policyholder.getClaims();
		
		theModel.addAttribute("policyholder", policyholder);
		
		theModel.addAttribute("claimList", claimList);
		
		theModel.addAttribute("user", user);
		
		return "policyholder-details";
	}
	
	@GetMapping("/policyholders/showPolicyDetails")
	public String showPolicyDetails(@ModelAttribute("policyHolderNumber") String policyholderNumber, 
									Model theModel,
									Principal principal) {
		
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		
		List<Policy> policyList = policyholder.getPolicies();
		
		User user = userService.getUserbyUsername(principal.getName());
		
		theModel.addAttribute("policyholder", policyholder);
		
		theModel.addAttribute("policyList", policyList);
		
		theModel.addAttribute("user", user);
		
		return "policies";
	}
	
	@PostMapping("/policyholders/editPolicyHolderDetails")
	public String editPolicyHolderDetails(@ModelAttribute("policyholder") PolicyHolder policyholder) {
		
		policyHolderService.savePolicyHolder(policyholder);
		
		return "policyholders-results";
	}
}
