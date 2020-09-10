package com.tom.common.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.tom.common.view.View;

@Entity
@Table(name = "ProvinceMasterTable")
public class Province {

	@JsonView(View.GenericView.class)
	private Long provinceId;
	@JsonView(View.GenericView.class)
	private String provinceName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
