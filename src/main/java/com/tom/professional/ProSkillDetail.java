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
@Table(name = "ProSkillDetailTable")
public class ProSkillDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7055534382408882196L;
	@JsonView(View.GenericView.class)
	private Long proSkillDetailId;
	@JsonView(View.GenericView.class)
	private String detailSkillName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProSkillDetailId() {
		return proSkillDetailId;
	}

	public void setProSkillDetailId(Long proSkillDetailId) {
		this.proSkillDetailId = proSkillDetailId;
	}

	public String getDetailSkillName() {
		return detailSkillName;
	}

	public void setDetailSkillName(String detailSkillName) {
		this.detailSkillName = detailSkillName;
	}

}
