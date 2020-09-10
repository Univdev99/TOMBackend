package com.tom.professional;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "ProjectScheduleByProTable")
public class ProjectScheduleByPro extends StatusDefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7616929428639297998L;
	private Long projectScheduleByProId;
	private Long projectScheduleId;
	private Timestamp scheduleDateTime1;
	private Timestamp scheduleDateTime2;
	private Timestamp scheduleDateTime3;
	private Boolean isNotAvailable = Boolean.FALSE;
	private Boolean isCompletedByAdmin = Boolean.FALSE;
	private Timestamp scheduleByAdmin;
	private Boolean isDeclineByProAfterMeeting = Boolean.FALSE;
	private Boolean isDeclineByFirmAfterMeeting = Boolean.FALSE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProjectScheduleByProId() {
		return projectScheduleByProId;
	}

	public void setProjectScheduleByProId(Long projectScheduleByProId) {
		this.projectScheduleByProId = projectScheduleByProId;
	}

	public Long getProjectScheduleId() {
		return projectScheduleId;
	}

	public void setProjectScheduleId(Long projectScheduleId) {
		this.projectScheduleId = projectScheduleId;
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

	public Boolean getIsNotAvailable() {
		return isNotAvailable;
	}

	public void setIsNotAvailable(Boolean isNotAvailable) {
		this.isNotAvailable = isNotAvailable;
	}

	public Boolean getIsCompletedByAdmin() {
		return isCompletedByAdmin;
	}

	public void setIsCompletedByAdmin(Boolean isCompletedByAdmin) {
		this.isCompletedByAdmin = isCompletedByAdmin;
	}

	public Timestamp getScheduleByAdmin() {
		return scheduleByAdmin;
	}

	public void setScheduleByAdmin(Timestamp scheduleByAdmin) {
		this.scheduleByAdmin = scheduleByAdmin;
	}

	public Boolean getIsDeclineByProAfterMeeting() {
		return isDeclineByProAfterMeeting;
	}

	public void setIsDeclineByProAfterMeeting(Boolean isDeclineByProAfterMeeting) {
		this.isDeclineByProAfterMeeting = isDeclineByProAfterMeeting;
	}

	public Boolean getIsDeclineByFirmAfterMeeting() {
		return isDeclineByFirmAfterMeeting;
	}

	public void setIsDeclineByFirmAfterMeeting(Boolean isDeclineByFirmAfterMeeting) {
		this.isDeclineByFirmAfterMeeting = isDeclineByFirmAfterMeeting;
	}

}
