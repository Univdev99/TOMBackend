package com.tom.professional;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.tom.common.view.View;

@Entity
@Table(name = "ProSkillCategoryTable")
public class ProSkillCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonView(View.GenericView.class)
	private Long proSkillCategoryId;
	@JsonView(View.GenericView.class)
	private String categorySkillName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProSkillCategoryId() {
		return proSkillCategoryId;
	}

	public void setProSkillCategoryId(Long proSkillCategoryId) {
		this.proSkillCategoryId = proSkillCategoryId;
	}

	public String getCategorySkillName() {
		return categorySkillName;
	}

	public void setCategorySkillName(String categorySkillName) {
		this.categorySkillName = categorySkillName;
	}

}
