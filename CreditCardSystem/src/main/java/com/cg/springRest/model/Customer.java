package com.cg.springRest.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Customer_Master")

public class Customer implements Serializable {

	@Id
	@Column(name = "Customer_Email")
	@NotEmpty(message = "Customer email id address should not be kept empty")
	@Email(message = "Enter a valid email id")
	private String customerEmail;

	@Column(name = "Customer_Full_Name")
	@NotEmpty(message = "Customer name should not be kept empty")
	// @Pattern(regexp = "[a-zA-Z]+", message = "Minimum 4 chars required and enter
	// a valid name")
	private String customerName;

	@Column(name = "Customer_Mobile_Number")
	@NotEmpty(message = "Customer number should not be kept empty")
	@Pattern(regexp = "[0-9]{10}", message = "only 10 digits allowed")
	private String customerNumber;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "Customer_User_Id", nullable = false)
	private User user;

	public Customer() {

	}

	public Customer(
			@NotEmpty(message = "Customer name should not be kept empty") @Size(min = 4, message = "Minimum 4 chars required") @Pattern(regexp = "[a-zA-Z]") String customerName,
			@NotEmpty(message = "Customer number should not be kept empty") @Size(min = 10, max = 10, message = "only 10 digits allowed") @Pattern(regexp = "[0-9]") String customerNumber,
			@NotEmpty(message = "Customer email id address should not be kept empty") @Email(message = "Enter a valid email id") String customerEmail,
			User user) {
		super();
		this.customerName = customerName;
		this.customerNumber = customerNumber;
		this.customerEmail = customerEmail;
		this.user = user;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerNumber=" + customerNumber + ", customerEmail="
				+ customerEmail + ", user=" + user + "]";
	}

}