package com.simplehomeinsurance.claims_management_system.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.simplehomeinsurance.claims_management_system.utils.DateUtils;
import com.simplehomeinsurance.claims_management_system.utils.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="claim")
public class Claim {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claims_sequence")
    @GenericGenerator(
        name = "claims_sequence", 
        strategy = "com.simplehomeinsurance.claims_management_system.idgenerator.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "49"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CM"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
	@Column(name="claim_number")
	private String claimNumber;
	
	@ManyToOne
	@JoinColumn(name="adjuster_number")
	private Adjuster adjuster;
	
	@ManyToOne
	@JoinColumn(name="policyholder_number")
	private PolicyHolder policyHolder;
	
	@ManyToOne
	@JoinColumn(name="csr_number")
	private CustomerServiceRep customerServiceRep;
	
	@Column(name="loss_type")
	private String lossType;
	
	@Column(name="incident_date")
	@Temporal(TemporalType.DATE)
	private Date incidentDate;
	
	@Column(name="filing_date")
	@Temporal(TemporalType.DATE)
	private Date filingDate;
	
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

	public String getIncidentDate() {
		return DateUtils.formatDate(incidentDate);
	}

	public void setIncidentDate(String incidentDate) {
		try {
			this.incidentDate = DateUtils.parseDate(incidentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getFilingDate() {
		return DateUtils.formatDate(filingDate);
	}

	public void setFilingDate(String filingDate) {
		try {
			this.filingDate = DateUtils.parseDate(filingDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public void setDeclinedClaim(DeclinedClaim declinedClaim) {
		this.declinedClaim = declinedClaim;
	}

	public List<ClaimPayment> getPayments() {
		return payments;
	}
	
	public void addPayment(ClaimPayment payment) {
		if (payments == null) {
			payments = new ArrayList<>();
		}
		payments.add(payment);
		payment.setClaim(this);
	}

	@Override
	public String toString() {
		return "Claim [claimNumber=" + claimNumber + ", adjuster=" + adjuster + ", policyHolder=" + policyHolder
				+ ", customerServiceRep=" + customerServiceRep + ", lossType=" + lossType + ", incidentDate="
				+ incidentDate + ", filingDate=" + filingDate + ", status=" + status + ", notes=" + notes + "]";
	}
	
	
}
