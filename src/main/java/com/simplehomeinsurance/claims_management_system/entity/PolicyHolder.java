package com.simplehomeinsurance.claims_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="policyholder")
public class PolicyHolder {
	
	@Id
	@Column(name="id")
	private String policyHolderNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="address")
	private String address;
	
	@OneToMany(mappedBy = "policyHolder", cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	private List<Policy> policies;
	
	@OneToMany(mappedBy = "policyHolder", fetch = FetchType.EAGER)
	private List<Claim> claims;
	
	public PolicyHolder() {
		
	}

	public String getPolicyHolderNumber() {
		return policyHolderNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public List<Claim> getClaims() {
		return claims;
	}
	
	public void addClaim(Claim claim) {
		if (claims == null) {
			claims = new ArrayList<>();
		}
		claims.add(claim);		
		claim.setPolicyHolder(this);
	}

	@Override
	public String toString() {
		return "PolicyHolder [policyHolderNumber=" + policyHolderNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}
	
	
}
