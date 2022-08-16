package com.simplehomeinsurance.claims_management_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="claim_payment")
public class ClaimPayment {
	
	@Id
	@Column(name="id")
	private int paymentNumber;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="claim_number")
	private Claim claim;
	
	@Column(name="payment_amount")
	private double paymentAmount;
	
	@Column(name="payment_date")
	private String paymentDate;
	
	@Column(name="notes")
	private String notes;
	
	public ClaimPayment() {
		
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
