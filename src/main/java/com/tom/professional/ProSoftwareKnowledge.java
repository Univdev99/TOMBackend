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
@Table(name = "ProSoftwareKnowledgeTable")
public class ProSoftwareKnowledge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159085262514399400L;
	private Long proSoftwateKnowledgeId;
	private String knowledge;
	private String otherValue1;
	private String otherValue2;
	private String otherValue3;
	private String otherValue4;
	private String otherValue5;
	@JsonBackReference
	private ProWorkExperience proWorkExperience;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProSoftwateKnowledgeId() {
		return proSoftwateKnowledgeId;
	}

	public void setProSoftwateKnowledgeId(Long proSoftwateKnowledgeId) {
		this.proSoftwateKnowledgeId = proSoftwateKnowledgeId;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getOtherValue1() {
		return otherValue1;
	}

	public void setOtherValue1(String otherValue1) {
		this.otherValue1 = otherValue1;
	}

	public String getOtherValue2() {
		return otherValue2;
	}

	public void setOtherValue2(String otherValue2) {
		this.otherValue2 = otherValue2;
	}

	public String getOtherValue3() {
		return otherValue3;
	}

	public void setOtherValue3(String otherValue3) {
		this.otherValue3 = otherValue3;
	}

	public String getOtherValue4() {
		return otherValue4;
	}

	public void setOtherValue4(String otherValue4) {
		this.otherValue4 = otherValue4;
	}

	public String getOtherValue5() {
		return otherValue5;
	}

	public void setOtherValue5(String otherValue5) {
		this.otherValue5 = otherValue5;
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
