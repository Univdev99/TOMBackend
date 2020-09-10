package com.tom.admin;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tom.common.beans.DefaultImport;
import com.tom.common.beans.StatusDefaultImport;

@Entity
@Table(name = "AdminProfitTable")
public class AdminProfit extends StatusDefaultImport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1076559777301870227L;
	private Long adminProfitId;
	private Float profit;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAdminProfitId() {
		return adminProfitId;
	}

	public void setAdminProfitId(Long adminProfitId) {
		this.adminProfitId = adminProfitId;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

}
