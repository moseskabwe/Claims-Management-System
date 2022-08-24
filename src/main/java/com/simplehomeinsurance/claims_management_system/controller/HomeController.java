package com.simplehomeinsurance.claims_management_system.controller;

import java.util.ArrayList;
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
		
		Long numberFire = claimService.getNumberOfFireClaims();
		Long numberDamage = claimService.getNumberOfDamageClaims();
		Long numberTheft = claimService.getNumberOfTheftClaims();
		Long numberNewClaims = claimService.getNumberOfNewClaims();
		Long numberInProgress = claimService.getNumberOfClaimsInProgress();
		Long numberFinalised = claimService.getNumberOfFinalisedClaims();
		Long numberTotal = claimService.getNumberTotalClaims();
		int finalisedAverage = (int) (Math.round(((double) numberFinalised / numberTotal)*10000.0)/100.0);
		
		ArrayList<Long> stats = new ArrayList<>();
		
		stats.add(numberFire);
		stats.add(numberDamage);
		stats.add(numberTheft);
		stats.add(numberNewClaims);
		stats.add(numberInProgress);
		
		model.addAttribute("dashboardClaimsList", dashboardClaims);
		
		model.addAttribute("stats", stats);
		
		model.addAttribute("finalisedAverage", finalisedAverage);
		
		return "dashboard";
	}
	
}
