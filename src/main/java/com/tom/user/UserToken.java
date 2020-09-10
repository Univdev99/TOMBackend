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

/**
 * @author CCJoshi
 *
 */
@Entity
@Table(name = "UserTokenTable")
public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userTokenId;
	private String tokenId;
	private User user;
	private Timestamp createdDate;
	private String node;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UserTokenId", unique = true, nullable = false)
	public Long getUserTokenId() {
		return userTokenId;
	}

	public void setUserTokenId(Long userTokenId) {
		this.userTokenId = userTokenId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
}
