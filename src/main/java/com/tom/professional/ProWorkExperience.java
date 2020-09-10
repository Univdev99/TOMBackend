package com.tom.professional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "ProWorkExperienceTable")
public class ProWorkExperience extends StatusDefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804486569862034898L;
	private Long proWorkExperienceId;
	private String selfDescription;
	private Boolean isCA;
	private String proHighLights;
	private Long professionalProfileId;

	@JsonManagedReference
	private List<ProWorkExperienceDetail> workExperienceDetail = new ArrayList<ProWorkExperienceDetail>();
	@JsonManagedReference
	private List<ProEducation> proEducation = new ArrayList<ProEducation>();
	@JsonManagedReference
	private List<ProSkill> proSkill = new ArrayList<ProSkill>();
	@JsonManagedReference
	private List<ProSoftwareKnowledge> proKnowledge = new ArrayList<ProSoftwareKnowledge>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProWorkExperienceId() {
		return proWorkExperienceId;
	}

	public void setProWorkExperienceId(Long proWorkExperienceId) {
		this.proWorkExperienceId = proWorkExperienceId;
	}

	public String getSelfDescription() {
		return selfDescription;
	}

	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}

	public Boolean getIsCA() {
		return isCA;
	}

	public void setIsCA(Boolean isCA) {
		this.isCA = isCA;
	}

	public String getProHighLights() {
		return proHighLights;
	}

	public void setProHighLights(String proHighLights) {
		this.proHighLights = proHighLights;
	}

	public Long getProfessionalProfileId() {
		return professionalProfileId;
	}

	public void setProfessionalProfileId(Long professionalProfileId) {
		this.professionalProfileId = professionalProfileId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proWorkExperience", orphanRemoval = true)
	public List<ProWorkExperienceDetail> getWorkExperienceDetail() {
		return workExperienceDetail;
	}

	public void setWorkExperienceDetail(List<ProWorkExperienceDetail> workExperienceDetail) {
		this.workExperienceDetail = workExperienceDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proWorkExperience", orphanRemoval = true)
	public List<ProEducation> getProEducation() {
		return proEducation;
	}

	public void setProEducation(List<ProEducation> proEducation) {
		this.proEducation = proEducation;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proWorkExperience", orphanRemoval = true)
	public List<ProSkill> getProSkill() {
		return proSkill;
	}

	public void setProSkill(List<ProSkill> proSkill) {
		this.proSkill = proSkill;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proWorkExperience", orphanRemoval = true)
	public List<ProSoftwareKnowledge> getProKnowledge() {
		return proKnowledge;
	}

	public void setProKnowledge(List<ProSoftwareKnowledge> proKnowledge) {
		this.proKnowledge = proKnowledge;
	}

}
