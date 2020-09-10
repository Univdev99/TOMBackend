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
@Table(name = "ProSkillMasterTable")
public class ProSkillMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8325280260533734539L;
	@JsonView(View.GenericView.class)
	private Long proSkillMasterId;
	@JsonView(View.GenericView.class)
	private String masterSkillName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProSkillMasterId() {
		return proSkillMasterId;
	}

	public void setProSkillMasterId(Long proSkillMasterId) {
		this.proSkillMasterId = proSkillMasterId;
	}

	public String getMasterSkillName() {
		return masterSkillName;
	}

	public void setMasterSkillName(String masterSkillName) {
		this.masterSkillName = masterSkillName;
	}

	

}
