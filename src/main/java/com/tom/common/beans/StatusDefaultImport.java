package com.tom.common.beans;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonView;
import com.tom.common.view.View;

/**
 *
 * Common DB Import Status NOTE: Extend this class in your POJO and to direct
 * access to "Status", "Created By", "Modified By", "Create Date", "Modified
 * Date" properties. (No Need to write code for it). For Use only "Status", you
 * need to code for it in your POJO.
 * 
 */
@MappedSuperclass
public class StatusDefaultImport extends DefaultImport implements Serializable {

	private static final long serialVersionUID = -1104745717362497062L;

	/**
	 * Use 0 for "InActive" and 1 for "Active"}
	 */

	/* The table will have a defaults value of 0 */
	@JsonView(View.GenericView.class)
	private Integer status = 1;

	/**
	 * Returns 0 as default if status is null
	 * 
	 */
	public Integer getStatus() {
		if (null != status) {
			return status;
		}
		return 0;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
