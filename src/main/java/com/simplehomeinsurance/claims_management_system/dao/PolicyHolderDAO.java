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
		Query<PolicyHolder> theQuery = currentSession.createQuery("from PolicyHolder", 
																	PolicyHolder.class);			
		return theQuery.getResultList();
	}

	public PolicyHolder getPolicyHolder(String policyHolderNumber) {
		Session currentSession = sessionFactory.getCurrentSession();		
		return currentSession.get(PolicyHolder.class, policyHolderNumber);
	}

	public void savePolicyHolder(PolicyHolder policyHolder) {
		Session currentSession = sessionFactory.getCurrentSession();		
		currentSession.saveOrUpdate(policyHolder);
	}
	
	public List<PolicyHolder> searchPolicyHolders(String searchTerm) {		
        Session currentSession = sessionFactory.getCurrentSession();      
        Query<PolicyHolder> query;
        
        if (searchTerm != null && searchTerm.trim().length() > 0) {
            query = currentSession.createQuery("from PolicyHolder where lower(first_name) like :name "
            										+ "or lower(last_name) like :name "
            										+ "or lower(policyHolderNumber) like :name", 
            										PolicyHolder.class);
            query.setParameter("name", "%" + searchTerm.toLowerCase() + "%");     
        } else {
            query = currentSession.createQuery("from PolicyHolder", PolicyHolder.class);            
        }       
        return query.getResultList();
	}
}
