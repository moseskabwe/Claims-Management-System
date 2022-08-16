package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;


@Repository
public class DeclinedClaimDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<DeclinedClaim> getDeclinedClaimList() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<DeclinedClaim> theQuery = currentSession.createQuery("from DeclinedClaim", DeclinedClaim.class);
				
		List<DeclinedClaim> declinedClaimList = theQuery.getResultList();
		
		return declinedClaimList;
	}

	public DeclinedClaim getDeclinedClaim(String declinedClaimNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		DeclinedClaim declinedClaim = currentSession.get(DeclinedClaim.class, declinedClaimNumber);
		
		return declinedClaim;
	}

	public void saveDeclinedClaim(DeclinedClaim declinedClaim) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(declinedClaim);
	}
}
