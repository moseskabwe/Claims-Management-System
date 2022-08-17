package com.simplehomeinsurance.claims_management_system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Policy;

@Repository
public class PolicyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Policy getPolicy(String policyNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Policy policy = currentSession.get(Policy.class, policyNumber);
		
		return policy;
	}
	
}
