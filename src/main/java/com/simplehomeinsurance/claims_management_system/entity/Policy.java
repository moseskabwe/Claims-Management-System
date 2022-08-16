package com.simplehomeinsurance.claims_management_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy {
	
	@Id
	@Column(name="policy_number")
	private String policyNumber;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="policyholder_number")
	private PolicyHolder policyHolder;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="property_number")
	private Property property;
	
	@Column(name="inception_date")
	private String inceptionDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="policy_type")
	private String policyType;
	
	@Column(name="in_force")
	private String inForce;
	
	public Policy() {
		
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public Property getProperty() {
		return property;
	}

	public String getInceptionDate() {
		return inceptionDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getPolicyType() {
		return policyType;
	}

	public String getInForce() {
		return inForce;
	}
	
	
	
}
