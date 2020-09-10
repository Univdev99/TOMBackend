package com.tom.common.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.tom.user.User;
import com.tom.util.CommonUtil;
import com.tom.util.DateUtil;

/**
 * 
 * @author pbhavsar
 *
 *         Common DB Import Default
 *
 *         NOTE : Extend this class in your POJO and to direct access to
 *         "Created By", "Modified By", "Create Date", "Modified Date"
 *         properties. (No Need to write code for it)
 *
 */

@MappedSuperclass
public abstract class DefaultImport implements Serializable {

	private static final long serialVersionUID = 4013959554770897895L;

	@JsonIdentityReference(alwaysAsId = true)
	private User createdById = CommonUtil.getContextUser();
	@JsonIdentityReference(alwaysAsId = true)
	private User modifiedById = CommonUtil.getContextUser();
	/*
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=
	 * "yyyy-MM-dd HH:mm:ss.SSS")
	 */
	private Timestamp createdDate = DateUtil.getCurrentTimestamp();
	/*	
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=
	 * "yyyy-MM-dd HH:mm:ss.SSS")
	 */
	private Timestamp modifiedDate = DateUtil.getCurrentTimestamp();

	public DefaultImport() {

	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdById", referencedColumnName = "UserId", insertable = true, updatable = false)
	public User getCreatedById() {
		return createdById;
	}

	public void setCreatedById(User createdById) {
		this.createdById = createdById;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modifiedById", referencedColumnName = "UserId", insertable = true, updatable = true)
	public User getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(User modifiedById) {
		this.modifiedById = modifiedById;
	}

	@Column(insertable = true, updatable = false)
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createDate) {
		this.createdDate = createDate;
	}

	@Column(insertable = true, updatable = true)
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}