package com.tom.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Chandani Joshi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionWrapper {
	private String resource;
	private String field;
	private String code;
	private String shortMessage;
	private String longMessage;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getLongMessage() {
		return longMessage;
	}

	public void setLongMessage(String longMessage) {
		this.longMessage = longMessage;
	}
}
