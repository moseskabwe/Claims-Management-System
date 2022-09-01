package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Policy;

@Repository
public class PolicyDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Policy getPolicy(String policyNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Policy.class, policyNumber);
	}
	
	public List<Policy> getPolicyList() {
		Session currentSession = sessionFactory.getCurrentSession();		
		Query<Policy> theQuery = currentSession.createQuery("from Policy", Policy.class);		
		return theQuery.getResultList();
	}
}
