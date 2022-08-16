package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.CustomerServiceRepDAO;
import com.simplehomeinsurance.claims_management_system.entity.CustomerServiceRep;

@Service
public class CustomerServiceRepService {
	
	@Autowired
	private CustomerServiceRepDAO customerServiceRepDAO;
	
	@Transactional
	public List<CustomerServiceRep> getCustomerServiceRepList(){
		
		return customerServiceRepDAO.getCustomerServiceRepList();
	}
	
	@Transactional
	public CustomerServiceRep getCustomerServiceRep(int customerServiceRepNumber){
		
		return customerServiceRepDAO.getCustomerServiceRep(customerServiceRepNumber);
	}
	
	@Transactional
	public void saveCustomerServiceRep(CustomerServiceRep customerServiceRep) {
		
		customerServiceRepDAO.saveCustomerServiceRep(customerServiceRep);
	}
}
