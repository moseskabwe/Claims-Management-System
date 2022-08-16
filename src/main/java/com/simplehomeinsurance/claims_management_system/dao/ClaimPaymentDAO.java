package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;

@Repository
public class ClaimPaymentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<ClaimPayment> getClaimPaymentList() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ClaimPayment> theQuery = currentSession.createQuery("from ClaimPayment", ClaimPayment.class);
				
		List<ClaimPayment> claimPaymentList = theQuery.getResultList();
		
		return claimPaymentList;
	}

	public ClaimPayment getDeclinedClaim(int paymentNumber) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		ClaimPayment claimPayment = currentSession.get(ClaimPayment.class, paymentNumber);
		
		return claimPayment;
	}

	public void saveClaimPayment(ClaimPayment claimPayment) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(claimPayment);
	}
		
}
