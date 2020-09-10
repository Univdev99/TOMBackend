package com.tom.common.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CalenderTable")
public class Calender extends DefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3428553469071252401L;
	private Long calenderId;
	private String eventTitle;
	private String eventDateTime;
	private String description;
	private String argFrom;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(Long calenderId) {
		this.calenderId = calenderId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArgFrom() {
		return argFrom;
	}

	public void setArgFrom(String argFrom) {
		this.argFrom = argFrom;
	}

}
