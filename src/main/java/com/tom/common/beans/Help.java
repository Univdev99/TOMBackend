package com.tom.common.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HelpTable")
public class Help extends DefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2796783902184895787L;

	private Long helpId;
	private String firstName;
	private String email;
	private String subject;
	private String message;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getHelpId() {
		return helpId;
	}

	public void setHelpId(Long helpId) {
		this.helpId = helpId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
