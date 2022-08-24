package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;

@Repository
public class ClaimDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Claim> getClaimsList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Claim> theQuery = currentSession.createQuery("from Claim order by filingDate desc", Claim.class);
		
		List<Claim> claims = theQuery.getResultList();
		
		return claims;
	}

	public void saveClaim(Claim theClaim) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(theClaim);
	}
	
	public void updateClaim(Claim theClaim) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(theClaim);
	}

	public Claim getClaim(String claimNumber) {

		Session currentSession = sessionFactory.getCurrentSession();

		Claim claim = currentSession.get(Claim.class, claimNumber);

		return claim;
	}
	
	public List<Claim> searchClaims(String searchTerm) {
		

        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Claim> theQuery;
        
        if (searchTerm != null && searchTerm.trim().length() > 0) {

            theQuery = currentSession.createQuery("from Claim where lower(claimNumber) like :name "
            										+ "or lower(policyHolder) like :name "
            										+ "or lower(policy) like :name", Claim.class);
            theQuery.setParameter("name", "%" + searchTerm.toLowerCase() + "%");
        
        } else {
            theQuery =currentSession.createQuery("from Claim", Claim.class);            
        }
        
        List<Claim> claimsList = theQuery.getResultList();
                     
        return claimsList;
	}
	
}
