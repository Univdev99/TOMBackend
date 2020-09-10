package com.tom.login;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "UserLoginDeviceHistoryTable")
public class UserLoginDeviceHistory implements Serializable{

	
	private static final long serialVersionUID = -3351082470178744133L;
	
	private Long userDeviceHistoryId;
	private Long  userId;
	private Long  userDeviceId;
	private Timestamp createdDate;

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   @Column(name = "UserDeviceHistoryId", unique = true, nullable = false)
	public Long getUserDeviceHistoryId() {
		return userDeviceHistoryId;
	}
	public void setUserDeviceHistoryId(Long userDeviceHistoryId) {
		this.userDeviceHistoryId = userDeviceHistoryId;
	}
	
	@Column (name = "UserId")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column (name = "UserDeviceId")
	public Long getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(Long userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
	
	@Column (name = "CreatedDate")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	


}
