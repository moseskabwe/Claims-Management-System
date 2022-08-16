package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;

@Repository
public class PolicyHolderDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PolicyHolder> getPolicyHolderList() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<PolicyHolder> theQuery = currentSession.createQuery("from PolicyHolder", PolicyHolder.class);
				
		List<PolicyHolder> policyHolderList = theQuery.getResultList();
		
		return policyHolderList;
	}

	public PolicyHolder getPolicyHolder(String policyHolderNumber) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		PolicyHolder policyHolder = currentSession.get(PolicyHolder.class, policyHolderNumber);
		
		return policyHolder;
	}

	public void savePolicyHolder(PolicyHolder policyHolder) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(policyHolder);
	}
}
