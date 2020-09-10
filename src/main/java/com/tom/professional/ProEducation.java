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
@Table(name = "ProEducationTable")
public class ProEducation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7935540841647853344L;
	private String proEducationId;
	private String year;
	private String degree;

	@JsonBackReference
	private ProWorkExperience proWorkExperience;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getProEducationId() {
		return proEducationId;
	}

	public void setProEducationId(String proEducationId) {
		this.proEducationId = proEducationId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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
