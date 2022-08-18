package com.simplehomeinsurance.claims_management_system.entity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Entity
@Table(name="policy")
public class Policy {
	
	@Id
	@Column(name="policy_number")
	private String policyNumber;
	
	@OneToOne
	@JoinColumn(name="policyholder_number")
	private PolicyHolder policyHolder;
	
	@OneToOne
	@JoinColumn(name="property_number")
	private Property property;
	
	@Column(name="inception_date")
	@Temporal(TemporalType.DATE)
	private Date inceptionDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="policy_type")
	private String policyType;
	
	@Column(name="in_force")
	private String inForce;
	
	@OneToMany(mappedBy = "policy")
	private List<Claim> claims;
	
	/*private LinkedHashMap<String, String> lossTypeOptions;*/
	
	public Policy() {
		
//		lossTypeOptions = new LinkedHashMap<>();
//		
//		lossTypeOptions.put("D", "Damage");
//		lossTypeOptions.put("F", "Fire");
//		lossTypeOptions.put("T", "Theft");
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
		return DateUtils.formatDate(endDate);
	}

	public String getEndDate() {
		return DateUtils.formatDate(endDate);
	}

	public String getPolicyType() {
		return policyType;
	}

	public String getInForce() {
		return inForce;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public List<Claim> getClaims() {
		return claims;
	}
	
	
//	public LinkedHashMap<String, String> getLossTypeOptions() {
//		return lossTypeOptions;
//	}
}
