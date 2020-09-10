package com.tom.professional;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ProWorkExperienceDetailTable")
public class ProWorkExperienceDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6454391429153844157L;
	private Long proWorkExperienceDetailId;
	private String startYear;
	private String endYear;
	private Boolean isAccountingFirm;
	private String posTitle;
	private String firmType;
	private String company;
	
	@JsonBackReference
	private ProWorkExperience proWorkExperience;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProWorkExperienceDetailId() {
		return proWorkExperienceDetailId;
	}

	public void setProWorkExperienceDetailId(Long proWorkExperienceDetailId) {
		this.proWorkExperienceDetailId = proWorkExperienceDetailId;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public Boolean getIsAccountingFirm() {
		return isAccountingFirm;
	}

	public void setIsAccountingFirm(Boolean isAccountingFirm) {
		this.isAccountingFirm = isAccountingFirm;
	}

	public String getPosTitle() {
		return posTitle;
	}

	public void setPosTitle(String posTitle) {
		this.posTitle = posTitle;
	}

	public String getFirmType() {
		return firmType;
	}

	public void setFirmType(String firmType) {
		this.firmType = firmType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proWorkExperienceId")
	public ProWorkExperience getProWorkExperience() {
		return proWorkExperience;
	}

	public void setProWorkExperience(ProWorkExperience proWorkExperience) {
		this.proWorkExperience = proWorkExperience;
	}

	
}
