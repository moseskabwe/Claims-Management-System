package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Adjuster;

@Repository
public class AdjusterDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Adjuster> getAdjustersList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Adjuster> theQuery = currentSession.createQuery("from Adjuster", Adjuster.class);
		
		List<Adjuster> adjustersList = theQuery.getResultList();
		
		return adjustersList;
	}

	public Adjuster getAdjuster(int adjusterNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Adjuster adjuster = currentSession.get(Adjuster.class, adjusterNumber);
		
		return adjuster;
	}

	public void saveAdjuster(Adjuster adjuster) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(adjuster);
		
	}
}
