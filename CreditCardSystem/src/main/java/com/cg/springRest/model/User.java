package com.cg.springRest.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@SuppressWarnings("serial")
@Entity
@Table(name = "User_Master")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable {

	@Id
	@Column(name = "User_Id", length = 5)
	//@SequenceGenerator(name = "ccs_seq", sequenceName = "ccs_sequence")
	@GeneratedValue
	private int userId;
	
	@Column(name = "User_Name")
	private String userName;
	
	@Column(name = "User_Password")
	private String userPassword;
	
	@Column(name = "User_Role_Id", length = 1)
	private int userRoleId;
	
	 @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user") 
	 private Customer customer;
	 
	 @Column(name="logged_In_Status")
	 private boolean loggedIn;
	 
	 
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String userPassword, int userRoleId) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRoleId = userRoleId;
		this.loggedIn=false;
	}

	public User(int userId, String userName, String userPassword, int userRoleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRoleId = userRoleId;
		this.loggedIn = false;
	}
	
	  public boolean isLoggedIn() {
	        return loggedIn;
	    }

	    public void setLoggedIn(boolean loggedIn) {
	        this.loggedIn = loggedIn;
	    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword,userRoleId, loggedIn);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userRoleId="
				+ userRoleId + ", customer=" + customer + ", loggedIn=" + loggedIn + "]";
	}

	
	 
}