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
	
	public List<PolicyHolder> searchPolicyHolders(String searchTerm) {
		

        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<PolicyHolder> theQuery;
        
        if (searchTerm != null && searchTerm.trim().length() > 0) {

            theQuery = currentSession.createQuery("from PolicyHolder where lower(first_name) like :name "
            										+ "or lower(last_name) like :name "
            										+ "or lower(policyHolderNumber) like :name", PolicyHolder.class);
            theQuery.setParameter("name", "%" + searchTerm.toLowerCase() + "%");
        
        } else {
            theQuery =currentSession.createQuery("from PolicyHolder", PolicyHolder.class);            
        }
        
        List<PolicyHolder> policyHolderList = theQuery.getResultList();
                     
        return policyHolderList;
	}
}
