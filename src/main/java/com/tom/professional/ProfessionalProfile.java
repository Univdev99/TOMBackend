package com.tom.professional;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tom.common.beans.City;
import com.tom.common.beans.Province;
import com.tom.common.beans.StatusDefaultImport;
import com.tom.common.serializer.Serializer;

@Entity
@Table(name = "ProfessionalProfileTable")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProfessionalProfile extends StatusDefaultImport implements Serializable {

	private static final long serialVersionUID = -6281714485214524173L;
	private Long professionalProfileId;
	private String firstName;
	private String lastName;
	private String address;
	@JsonSerialize(using = Serializer.class)
	private City city;
	@JsonSerialize(using = Serializer.class)
	private Province province;
	private String zipCode;
	private String picBase64;
	private Float hourlyFee;
	private Long userId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProfessionalProfileId() {
		return professionalProfileId;
	}

	public void setProfessionalProfileId(Long professionalProfileId) {
		this.professionalProfileId = professionalProfileId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provinceId")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPicBase64() {
		return picBase64;
	}

	public void setPicBase64(String picBase64) {
		this.picBase64 = picBase64;
	}

	public Float getHourlyFee() {
		return hourlyFee;
	}

	public void setHourlyFee(Float hourlyFee) {
		this.hourlyFee = hourlyFee;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
