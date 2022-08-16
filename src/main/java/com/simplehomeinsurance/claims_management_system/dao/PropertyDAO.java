package com.simplehomeinsurance.claims_management_system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Property;

@Repository
public class PropertyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Property getProperty(String propertyNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Property property = currentSession.get(Property.class, propertyNumber);
		
		return property;
	}

}
