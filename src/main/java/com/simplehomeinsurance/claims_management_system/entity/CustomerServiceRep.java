package com.simplehomeinsurance.claims_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer_service_rep")
public class CustomerServiceRep {
	
	@Id
	@Column(name="id")
	private int customerServiceRepNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customerServiceRep", cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
			 			CascadeType.MERGE, CascadeType.REFRESH})
	private List<Claim> claims;
	
	public CustomerServiceRep() {
		
	}

	public int getCustomerServiceRepNumber() {
		return customerServiceRepNumber;
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

	public List<Claim> getClaims() {
		return claims;
	}
	
	public void addClaim(Claim claim) {
		if (claims == null) {
			claims = new ArrayList<>();
		}
		claims.add(claim);
		claim.setCustomerServiceRep(this);
	}
}
