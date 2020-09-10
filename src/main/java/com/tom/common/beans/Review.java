package com.tom.common.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ReviewTable")
public class Review extends DefaultImport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1076559777301870227L;
	private Long reviewId;
	private Long projectId;
	private Long firmProfileId;
	private Long proProfileId;
	private String reviewFor;
	private Float rating;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public String getReviewFor() {
		return reviewFor;
	}

	public void setReviewFor(String reviewFor) {
		this.reviewFor = reviewFor;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

}
