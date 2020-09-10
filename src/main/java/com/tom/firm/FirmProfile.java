package com.tom.firm;

import java.io.Serializable;

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
import com.tom.common.beans.Province;
import com.tom.common.beans.StatusDefaultImport;
import com.tom.common.serializer.Serializer;

@Entity
@Table(name = "FirmProfileTable")
public class FirmProfile extends StatusDefaultImport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480154338862379214L;
	private Long firmProfileId;
	private String businessName;
	private String branchName;
	private String streetAddress;
	private String cityId;
	private String cityName;
	private String zipCode;
	private Long provinceId;
	private String businessDesc;
	private Boolean isAccountingFirm;
	private Boolean isRegisteredFirm;
	private String hstNumber;
	private String webSite;
	private String billEmailAddress;
	private Boolean isBothAddressSame;
	private String billStreetAddress;
	private String billCityId;
	private String billCityName;
	private Long billProvinceId;
	private String billZipcode;

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String workEmail;

	private Long userId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getFirmProfileId() {
		return firmProfileId;
	}

	public void setFirmProfileId(Long firmProfileId) {
		this.firmProfileId = firmProfileId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public Boolean getIsAccountingFirm() {
		return isAccountingFirm;
	}

	public void setIsAccountingFirm(Boolean isAccountingFirm) {
		this.isAccountingFirm = isAccountingFirm;
	}

	public Boolean getIsRegisteredFirm() {
		return isRegisteredFirm;
	}

	public void setIsRegisteredFirm(Boolean isRegisteredFirm) {
		this.isRegisteredFirm = isRegisteredFirm;
	}

	public String getHstNumber() {
		return hstNumber;
	}

	public void setHstNumber(String hstNumber) {
		this.hstNumber = hstNumber;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getBillEmailAddress() {
		return billEmailAddress;
	}

	public void setBillEmailAddress(String billEmailAddress) {
		this.billEmailAddress = billEmailAddress;
	}

	public Boolean getIsBothAddressSame() {
		return isBothAddressSame;
	}

	public void setIsBothAddressSame(Boolean isBothAddressSame) {
		this.isBothAddressSame = isBothAddressSame;
	}

	public String getBillStreetAddress() {
		return billStreetAddress;
	}

	public void setBillStreetAddress(String billStreetAddress) {
		this.billStreetAddress = billStreetAddress;
	}

	public String getBillCityId() {
		return billCityId;
	}

	public void setBillCityId(String billCityId) {
		this.billCityId = billCityId;
	}

	public String getBillCityName() {
		return billCityName;
	}

	public void setBillCityName(String billCityName) {
		this.billCityName = billCityName;
	}

	public String getBillZipcode() {
		return billZipcode;
	}

	public void setBillZipcode(String billZipcode) {
		this.billZipcode = billZipcode;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getBillProvinceId() {
		return billProvinceId;
	}

	public void setBillProvinceId(Long billProvinceId) {
		this.billProvinceId = billProvinceId;
	}

}
