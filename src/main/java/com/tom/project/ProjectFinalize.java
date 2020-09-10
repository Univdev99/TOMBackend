package com.tom.project;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.DefaultImport;

@Entity
@Table(name = "ProjectFinalizeTable")
public class ProjectFinalize extends DefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7998410769865619741L;
	private Long projectFinalizeId;
	private Long projectId;
	private Long proProfileId;
	private Float requiredHours;
	private Date deadLine;
	private String workScope;
	private String amends;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectFinalizeId() {
		return projectFinalizeId;
	}

	public void setProjectFinalizeId(Long projectFinalizeId) {
		this.projectFinalizeId = projectFinalizeId;
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
	
	

}
