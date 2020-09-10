package com.tom.professional;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.DefaultImport;
import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "ProjectAdditionalTimeTable")
public class ProjectAdditionalTime extends StatusDefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7998410769865619741L;
	private Long projectAdditionalTimeId;
	private Long projectId;
	private Long proProfileId;
	private Float requiredHours;
	private Date deadLine;
	private String workScope;
	private String amends;
	private Long projectFinalizeId;
	private Boolean isDeclineByFirm;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectAdditionalTimeId() {
		return projectAdditionalTimeId;
	}

	public void setProjectAdditionalTimeId(Long projectAdditionalTimeId) {
		this.projectAdditionalTimeId = projectAdditionalTimeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getProProfileId() {
		return proProfileId;
	}

	public void setProProfileId(Long proProfileId) {
		this.proProfileId = proProfileId;
	}

	public Float getRequiredHours() {
		return requiredHours;
	}

	public void setRequiredHours(Float requiredHours) {
		this.requiredHours = requiredHours;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getWorkScope() {
		return workScope;
	}

	public void setWorkScope(String workScope) {
		this.workScope = workScope;
	}

	public String getAmends() {
		return amends;
	}

	public void setAmends(String amends) {
		this.amends = amends;
	}

	public Long getProjectFinalizeId() {
		return projectFinalizeId;
	}

	public void setProjectFinalizeId(Long projectFinalizeId) {
		this.projectFinalizeId = projectFinalizeId;
	}

	public Boolean getIsDeclineByFirm() {
		return isDeclineByFirm;
	}

	public void setIsDeclineByFirm(Boolean isDeclineByFirm) {
		this.isDeclineByFirm = isDeclineByFirm;
	}

}
