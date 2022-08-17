package com.simplehomeinsurance.claims_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.Adjuster;
import com.simplehomeinsurance.claims_management_system.service.AdjusterService;


@Controller
@RequestMapping("dashboard/adjuster")
public class AdjusterController {
	
	@Autowired
	private AdjusterService adjusterService;
	
	@GetMapping("/showAdjusterDetails")
	public String showAdjusterDetails(@ModelAttribute("adjusterNumber") int adjusterNumber, Model theModel) {
		
		Adjuster adjuster = adjusterService.getAdjuster(adjusterNumber);
		
		theModel.addAttribute("adjuster", adjuster);
		
		return "adjuster-details";
		
	}
	
	@PostMapping("/showCsrDetails/editAdjusterDetails")
	public String editAdjusterDetails(@ModelAttribute("adjuster") Adjuster adjuster) {
		
		adjusterService.saveAdjuster(adjuster);
		
		return "change-adjuster-details";
	}
}
