package com.tom.common.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.tom.common.view.View;

@Entity
@Table(name = "CityMasterTable")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 424273417479291912L;
	@JsonView(View.GenericView.class)
	private Long cityId;
	@JsonView(View.GenericView.class)
	private String cityName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
