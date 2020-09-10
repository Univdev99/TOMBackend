package com.tom.project;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "ProjectScheduleTable")
public class ProjectSchedule extends StatusDefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8196982957143364756L;
	private Long projectScheduleId;
	private Long firmProfileId;
	private Long proProfileId;
	private Long projectId;
	private Timestamp scheduleDateTime1;
	private Timestamp scheduleDateTime2;
	private Timestamp scheduleDateTime3;
	private Boolean isDeclineByPro = Boolean.FALSE;
	private Boolean isDeclineByFirm = Boolean.FALSE;
	private Float adminProfit;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectScheduleId() {
		return projectScheduleId;
	}

	public void setProjectScheduleId(Long projectScheduleId) {
		this.projectScheduleId = projectScheduleId;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Timestamp getScheduleDateTime1() {
		return scheduleDateTime1;
	}

	public void setScheduleDateTime1(Timestamp scheduleDateTime1) {
		this.scheduleDateTime1 = scheduleDateTime1;
	}

	public Timestamp getScheduleDateTime2() {
		return scheduleDateTime2;
	}

	public void setScheduleDateTime2(Timestamp scheduleDateTime2) {
		this.scheduleDateTime2 = scheduleDateTime2;
	}

	public Timestamp getScheduleDateTime3() {
		return scheduleDateTime3;
	}

	public void setScheduleDateTime3(Timestamp scheduleDateTime3) {
		this.scheduleDateTime3 = scheduleDateTime3;
	}

	public Boolean getIsDeclineByPro() {
		return isDeclineByPro;
	}

	public void setIsDeclineByPro(Boolean isDeclineByPro) {
		this.isDeclineByPro = isDeclineByPro;
	}

	public Boolean getIsDeclineByFirm() {
		return isDeclineByFirm;
	}

	public void setIsDeclineByFirm(Boolean isDeclineByFirm) {
		this.isDeclineByFirm = isDeclineByFirm;
	}

	public Float getAdminProfit() {
		return adminProfit;
	}

	public void setAdminProfit(Float adminProfit) {
		this.adminProfit = adminProfit;
	}

}
