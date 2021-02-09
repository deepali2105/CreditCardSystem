package com.cg.springRest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Transaction_Master")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Transaction {

	@Id
	@Column(name = "trans_id")
	@SequenceGenerator(name = "trans_id_seq", sequenceName = "trans_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_id_seq")
	private long transId;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "trans_value")
	private long transValue;

	@Column(name = "trans_date")
	private Date transDate;
	
	@Column(name="creditCardNumber")
	private long creditCardNumber;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction(long transId, String paymentMethod, long transValue, Date transDate,
			long creditCardNumber) {
		super();
		this.transId = transId;
		this.paymentMethod = paymentMethod;
		this.transValue = transValue;
		this.transDate = transDate;
		this.creditCardNumber = creditCardNumber;
	}

	public Transaction(String paymentMethod, long transValue, Date transDate,  long creditCardNumber) {
		super();
		this.paymentMethod = paymentMethod;
		this.transValue = transValue;
		this.transDate = transDate;
		this.creditCardNumber = creditCardNumber;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public long getTransValue() {
		return transValue;
	}

	public void setTransValue(long transValue) {
		this.transValue = transValue;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	

	public long getcreditCardNumber() {
		return creditCardNumber;
	}

	public void setcreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", paymentMethod=" + paymentMethod + ", transValue=" + transValue
				+ ", transDate=" + transDate + ", creditCardNumber=" + creditCardNumber + "]";
	}
}