package com.cg.springRest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "CreditCard")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class CreditCard implements Serializable {

	@Id
	@Column(name = "CreditCard_Number", length = 16)
	@SequenceGenerator(name = "ccs_no_seq", sequenceName = "ccs_no_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ccs_no_seq")
	private Long creditcardNo;

	@Column(name = "creditcard_limit")
	private double creditcardLimit;

	
	@Column(name = "card_type")
	private String cardType;

	@Column(name = "valid_from")
	private String validFrom;

	@Column(name = "valid_to")
	private String validTo;

	@Column(name = "available_balance")
	private long availableBalance;

	@Column(name = "Customer_User_Id")
	private int customerId;
	
	
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	

	public CreditCard(@NotBlank double creditcardLimit, @NotBlank String cardType, String validFrom, String validTo,
			long availableBalance, int customerId) {
		super();
		this.creditcardLimit = creditcardLimit;
		this.cardType = cardType;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.availableBalance = availableBalance;
		this.customerId = customerId;
	}

	public Long getCreditcardNo() {
		return creditcardNo;
	}

	public void setCreditcardNo(Long creditcardNo) {
		this.creditcardNo = creditcardNo;
	}

	public double getCreditcardLimit() {
		return creditcardLimit;
	}

	public void setCreditcardLimit(double creditcardLimit) {
		this.creditcardLimit = creditcardLimit;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public long getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(long availableBalance) {
		this.availableBalance = availableBalance;
	}

	/*
	 * public Customer getuser() { return user; }
	 * 
	 * public void setuser(Customer user) { this.user = user; }
	 */
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CreditCard [creditcardNo=" + creditcardNo + ", creditcardLimit=" + creditcardLimit + ", cardType="
				+ cardType + ", validFrom=" + validFrom + ", validTo=" + validTo + ", availableBalance="
				+ availableBalance + ", customerId=" + customerId + "]";
	}



}