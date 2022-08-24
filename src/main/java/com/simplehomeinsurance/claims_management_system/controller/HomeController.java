package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;

@Controller
public class HomeController {
	
	@Autowired
	private ClaimService claimService;
	
	@GetMapping("dashboard")
	public String showDashboard(Model model) {
		
		List<Claim> dashboardClaims = claimService.getDashboardClaimsList();
		
		model.addAttribute("dashboardClaimsList", dashboardClaims);
		
		return "dashboard";
	}
	
}
