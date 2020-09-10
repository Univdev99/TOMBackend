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
@Table(name = "ProSkillTable")
public class ProSkill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3613774604361832399L;
	private Long proSkillId;
	private Long proSkillMasterId;
	private Long proSkillCategoryId;
	private Long proSkillDetailId;
	@JsonBackReference
	private ProWorkExperience proWorkExperience;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProSkillId() {
		return proSkillId;
	}

	public void setProSkillId(Long proSkillId) {
		this.proSkillId = proSkillId;
	}

	public Long getProSkillMasterId() {
		return proSkillMasterId;
	}

	public void setProSkillMasterId(Long proSkillMasterId) {
		this.proSkillMasterId = proSkillMasterId;
	}

	public Long getProSkillCategoryId() {
		return proSkillCategoryId;
	}

	public void setProSkillCategoryId(Long proSkillCategoryId) {
		this.proSkillCategoryId = proSkillCategoryId;
	}

	public Long getProSkillDetailId() {
		return proSkillDetailId;
	}

	public void setProSkillDetailId(Long proSkillDetailId) {
		this.proSkillDetailId = proSkillDetailId;
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
