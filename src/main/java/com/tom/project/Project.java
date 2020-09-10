package com.tom.project;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tom.common.beans.City;
import com.tom.common.beans.DefaultImport;
import com.tom.common.serializer.Serializer;

@Entity
@Table(name = "ProjectTable")
public class Project extends DefaultImport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5659254963341293890L;
	private Long projectId;
	private String isShortOrLong;
	private String projectName;
	private String expertise;
	@JsonSerialize(using = Serializer.class)
	private City city;
	private String workLocation;
	private Double hours;
	private String hoursDuration;
	private String projectDescription;
	private Date targetDate;
	private String status = "Open";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getIsShortOrLong() {
		return isShortOrLong;
	}

	public void setIsShortOrLong(String isShortOrLong) {
		this.isShortOrLong = isShortOrLong;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public String getHoursDuration() {
		return hoursDuration;
	}

	public void setHoursDuration(String hoursDuration) {
		this.hoursDuration = hoursDuration;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
