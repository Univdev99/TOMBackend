package com.tom.professional;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProWorkAvailabilityTable")
public class ProWorkAvailability implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98177027441715424L;
	private Long proWorkAvailabilityId;
	private Date startDate;
	private Date lastDate;
	private String dateRange;
	private String workAvailability;
	private String locationAvailability;
	private Boolean isFullTime;
//	@JsonSerialize(using = Serializer.class)
	private Long professionalProfileId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProWorkAvailabilityId() {
		return proWorkAvailabilityId;
	}

	public void setProWorkAvailabilityId(Long proWorkAvailabilityId) {
		this.proWorkAvailabilityId = proWorkAvailabilityId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getWorkAvailability() {
		return workAvailability;
	}

	public void setWorkAvailability(String workAvailability) {
		this.workAvailability = workAvailability;
	}

	public String getLocationAvailability() {
		return locationAvailability;
	}

	public void setLocationAvailability(String locationAvailability) {
		this.locationAvailability = locationAvailability;
	}

	public Boolean getIsFullTime() {
		return isFullTime;
	}

	public void setIsFullTime(Boolean isFullTime) {
		this.isFullTime = isFullTime;
	}

	public Long getProfessionalProfileId() {
		return professionalProfileId;
	}

	public void setProfessionalProfileId(Long professionalProfileId) {
		this.professionalProfileId = professionalProfileId;
	}

}
