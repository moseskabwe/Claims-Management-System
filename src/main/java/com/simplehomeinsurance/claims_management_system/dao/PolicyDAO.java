package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;

@Repository
public class PolicyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Policy getPolicy(String policyNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Policy policy = currentSession.get(Policy.class, policyNumber);
		
		return policy;
	}
	
	public List<Policy> getPolicyList() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Policy> theQuery = currentSession.createQuery("from Policy", Policy.class);
				
		List<Policy> policyList = theQuery.getResultList();
		
		return policyList;
	}
}
