package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.ClaimDAO;
import com.simplehomeinsurance.claims_management_system.entity.Claim;

@Service
public class ClaimService {
	
	@Autowired	
	private ClaimDAO claimDAO;
	
	@Transactional
	public List<Claim> getClaimsList() {
		
		return claimDAO.getClaimsList();
	}
	
	@Transactional
	public Claim getClaim(String claimNumber) {
		
		return claimDAO.getClaim(claimNumber);
	}
	
	@Transactional
	public void saveClaim(Claim theClaim) {
		
		claimDAO.saveClaim(theClaim);
	}
	
	@Transactional
	public void updateClaim(Claim theClaim) {
		
		claimDAO.updateClaim(theClaim);
	}
	
	@Transactional
	public List<Claim> searchClaims(String searchTerm) {
		return claimDAO.getClaimsList();
	}
	
}
