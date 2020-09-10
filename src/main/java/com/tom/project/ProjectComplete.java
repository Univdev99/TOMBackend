package com.tom.project;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.DefaultImport;

@Entity
@Table(name = "ProjectCompleteTable")
public class ProjectComplete extends DefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5767264321214128834L;
	private Long projectCompleteId;
	private Long projectId;
	private Long firmProfileId;
	private Long proProfileId;
	private Boolean isCompletedByPro = false;
	private Boolean isCompletedByFirm = false;
	private Boolean isCompletedByAdmin = false;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectCompleteId() {
		return projectCompleteId;
	}

	public void setProjectCompleteId(Long projectCompleteId) {
		this.projectCompleteId = projectCompleteId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getFirmProfileId() {
		return firmProfileId;
	}

	public void setFirmProfileId(Long firmProfileId) {
		this.firmProfileId = firmProfileId;
	}

	public Long getProProfileId() {
		return proProfileId;
	}

	public void setProProfileId(Long proProfileId) {
		this.proProfileId = proProfileId;
	}

	public Boolean getIsCompletedByPro() {
		return isCompletedByPro;
	}

	public void setIsCompletedByPro(Boolean isCompletedByPro) {
		this.isCompletedByPro = isCompletedByPro;
	}

	public Boolean getIsCompletedByFirm() {
		return isCompletedByFirm;
	}

	public void setIsCompletedByFirm(Boolean isCompletedByFirm) {
		this.isCompletedByFirm = isCompletedByFirm;
	}

	public Boolean getIsCompletedByAdmin() {
		return isCompletedByAdmin;
	}

	public void setIsCompletedByAdmin(Boolean isCompletedByAdmin) {
		this.isCompletedByAdmin = isCompletedByAdmin;
	}

}
