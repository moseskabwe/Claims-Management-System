package com.simplehomeinsurance.claims_management_system.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.simplehomeinsurance.claims_management_system.idgenerator.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="claim")
public class Claim {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claims_sequence")
    @GenericGenerator(
        name = "claims_sequence", 
        strategy = "com.hibernatetutorial.demo.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "49"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CM"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
	@Column(name="claim_number")
	private String claimNumber;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
						 CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="adjuster_number")
	private Adjuster adjuster;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
			 CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="policyholder_number")
	private PolicyHolder policyHolder;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
			 CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="csr_number")
	private CustomerServiceRep customerServiceRep;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
			 CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="policy_number")
	private Policy policy;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
			 CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="property_number")
	private Property property;
	
	@Column(name="loss_type")
	private String lossType;
	
	@Column(name="incident_date")
	private String incidentDate;
	
	@Column(name="filing_date")
	private String filingDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@OneToOne(mappedBy = "claim", cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	private DeclinedClaim declinedClaim;
	
	@OneToMany(mappedBy = "claim", cascade= {CascadeType.PERSIST, CascadeType.DETACH, 
 			CascadeType.MERGE, CascadeType.REFRESH})
	private List<ClaimPayment> payments;
	
	public Claim() {
	
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	
	public Adjuster getAdjuster() {
		return adjuster;
	}

	public void setAdjuster(Adjuster adjuster) {
		this.adjuster = adjuster;
	}

	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}

	public CustomerServiceRep getCustomerServiceRep() {
		return customerServiceRep;
	}

	public void setCustomerServiceRep(CustomerServiceRep customerServiceRep) {
		this.customerServiceRep = customerServiceRep;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public String getLossType() {
		return lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public DeclinedClaim getDeclinedClaim() {
		return declinedClaim;
	}

	public List<ClaimPayment> getPayments() {
		return payments;
	}
	
}
