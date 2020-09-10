package com.tom.user;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SessionDetailTable")
public class SessionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long sessionId;
	private User user;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private Integer status = 1;
	private String ipAddress;
	private Long userTokenId;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SessionId", unique = true, nullable = false)
	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getUserTokenId() {
		return userTokenId;
	}

	public void setUserTokenId(Long userTokenId) {
		this.userTokenId = userTokenId;
	}

}
