package com.tom.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.omnione.common.beans.UserEBPMap;
//import com.omnione.common.view.View;
//import com.omnione.ebpl.role.Role;
import com.tom.util.DateUtil;

@Entity
@Table(name = "UserTable")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -7338904400941157826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId", unique = true, nullable = false, length = 36)
	private Long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String workEmail;
	private String businessName;
	private String category;

	private Long roleId;
	private Boolean status = Boolean.FALSE;
	private String userStatus = null;
	private String forgotToken;
	private Timestamp forgotTokenTime;
	private Long createdById;
	private Long modifiedById;
	private Timestamp createdDate = DateUtil.getCurrentTimestamp();
	private Timestamp modifiedDate = DateUtil.getCurrentTimestamp();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public Long getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(Long modifiedById) {
		this.modifiedById = modifiedById;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getForgotToken() {
		return forgotToken;
	}

	public void setForgotToken(String forgotToken) {
		this.forgotToken = forgotToken;
	}

	public Timestamp getForgotTokenTime() {
		return forgotTokenTime;
	}

	public void setForgotTokenTime(Timestamp forgotTokenTime) {
		this.forgotTokenTime = forgotTokenTime;
	}

}
