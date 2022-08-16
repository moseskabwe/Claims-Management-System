package com.simplehomeinsurance.claims_management_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="declined_claims")
public class DeclinedClaim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int declinedClaimNumber;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
			 			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="claim_number")
	private Claim claim;
	
	@Column(name="declined_date")
	private String declinedDate;
	
	public DeclinedClaim() {
		
	}

	public int getDeclinedClaimNumber() {
		return declinedClaimNumber;
	}

	public void setDeclinedClaimNumber(int declinedClaimNumber) {
		this.declinedClaimNumber = declinedClaimNumber;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String getDeclinedDate() {
		return declinedDate;
	}

	public void setDeclinedDate(String declinedDate) {
		this.declinedDate = declinedDate;
	}
	
}
