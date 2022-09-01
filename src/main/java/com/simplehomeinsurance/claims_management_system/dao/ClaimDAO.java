package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Claim;

@Repository
public class ClaimDAO {	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Claim> getClaimsList() {	
		Session currentSession = sessionFactory.getCurrentSession();	
		Query<Claim> theQuery = currentSession.createQuery("from Claim order by filingDate desc", 
															Claim.class);		
		return theQuery.getResultList();
	}
	
	public List<Claim> getMyOutstandingClaims(int userId) {		
		Session currentSession = sessionFactory.getCurrentSession();		
		Query<Claim> theQuery = currentSession.createQuery("from Claim where adjuster = " + userId 
				+ " and status in ('In Progress','First Notice') order by filingDate desc", 
				Claim.class);		
		return theQuery.getResultList();
	}

	public List<Claim> getMyClaims(int userId) {		
		Session currentSession = sessionFactory.getCurrentSession();		
		Query<Claim> theQuery = currentSession.createQuery("from Claim where adjuster = " + userId 
													+ " order by filingDate desc", Claim.class);		
		return theQuery.getResultList();
	}
	
	public List<Claim> getDashboardClaimsList() {		
		Session currentSession = sessionFactory.getCurrentSession();		
		Query<Claim> theQuery = currentSession.createQuery("from Claim where status in "
				+ "('In Progress',	'First Notice') order by filingDate desc", Claim.class);
		return theQuery.getResultList();
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
		return currentSession.get(Claim.class, claimNumber);
	}
	
	public Long getNumberOfFireClaims() {	
		Session currentSession = sessionFactory.getCurrentSession();		
		return (Long) currentSession.createQuery("select count(*) from Claim where lossType = 'Fire'")
									.uniqueResult();
	}
	
	public Long getNumberOfDamageClaims() {		
		Session currentSession = sessionFactory.getCurrentSession();		
		return (Long) currentSession.createQuery("select count(*) from Claim where lossType = 'Damage'")
									.uniqueResult();
	}
	
	public Long getNumberOfTheftClaims() {		
		Session currentSession = sessionFactory.getCurrentSession();			
		return (Long) currentSession.createQuery("select count(*) from Claim where lossType = 'Theft'")
									.uniqueResult();
	}
	
	public Long getNumberOfNewClaims() {
		Session currentSession = sessionFactory.getCurrentSession();					
		return (Long) currentSession.createQuery("select count(*) from Claim where status = "
												 + "'First Notice'").uniqueResult();
	}
	
	public Long getNumberOfClaimsInProgress() {		
		Session currentSession = sessionFactory.getCurrentSession();	
					
		return (Long) currentSession.createQuery("select count(*) from Claim where status  = "
												 + "'In Progress'").uniqueResult();
	}
	
	public Long getNumberOfFinalisedClaims() {	
		Session currentSession = sessionFactory.getCurrentSession();			
		return (Long) currentSession.createQuery("select count(*) from Claim where status = 'Finalised'")
									.uniqueResult();
	}
	
	public Long getNumberTotalClaims() {
		Session currentSession = sessionFactory.getCurrentSession();				
		return (Long) currentSession.createQuery("select count(*) from Claim").uniqueResult();
	}	
}
