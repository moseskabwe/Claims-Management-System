package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.CustomerServiceRep;

@Repository
public class CustomerServiceRepDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<CustomerServiceRep> getCustomerServiceRepList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<CustomerServiceRep> theQuery = currentSession.createQuery("from CustomerServiceRep", CustomerServiceRep.class);
				
		List<CustomerServiceRep> customerServiceRepList = theQuery.getResultList();
		
		return customerServiceRepList;
	}

	public CustomerServiceRep getCustomerServiceRep(int customerServiceRepNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		CustomerServiceRep customerServiceRep = currentSession.get(CustomerServiceRep.class, customerServiceRepNumber);
		
		return customerServiceRep;
	}
	
	public void saveCustomerServiceRep(CustomerServiceRep customerServiceRep) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customerServiceRep);
	}

}
