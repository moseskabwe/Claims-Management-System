package com.simplehomeinsurance.claims_management_system.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.PolicyDAO;
import com.simplehomeinsurance.claims_management_system.entity.Policy;

@Service
public class PolicyService {
	
	@Autowired
	private PolicyDAO policyDAO;
	
	@Transactional
	public Policy getPolicy(String policyNumber) {
		
		return policyDAO.getPolicy(policyNumber);
	}
}
