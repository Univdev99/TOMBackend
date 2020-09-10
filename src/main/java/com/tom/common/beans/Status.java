package com.tom.common.beans;

import java.util.List;

import com.tom.common.constant.Constant;
import com.tom.common.exception.ExceptionWrapper;

/**
 * This class is used to send response with messages.
 * 
 * @author Chandani Joshi
 *
 */
public class Status implements Constant {
	private int apiCode;
	private String message;
	private List<ExceptionWrapper> exceptionWrappers;
	private Object data;

	/**
	 * @author BSATHVARA
	 * @ModifiedBy BSATHVARA
	 * @ModifiedDate Mar 24, 2017
	 */
	public Status() {
		this.apiCode = ConstantStatus.OK_NO_MESSAGE.value();
		this.message = SUCCESS;
	}

	/**
	 * @param apiCode
	 * @param message
	 * @author BSATHVARA
	 * @ModifiedBy BSATHVARA
	 * @ModifiedDate Mar 24, 2017
	 */
	public Status(ConstantStatus status, String message) {
		this.apiCode = status.value();
		this.message = message;
	}

	/**
	 * @param apiCode
	 * @param message
	 * @param exceptionWrapper
	 * @author BSATHVARA
	 * @ModifiedBy BSATHVARA
	 * @ModifiedDate Mar 24, 2017
	 */
	public Status(ConstantStatus status, String message, List<ExceptionWrapper> exceptionWrapper) {
		this.apiCode = status.value();
		this.message = message;
		this.exceptionWrappers = exceptionWrapper;
	}

	/**
	 * @param code
	 * @param message
	 * 
	 * @deprecated reason this method is deprecated </br>
	 * {will be removed in next version} </br>
	 * use {@link new Status(ConstantStatus.OK, "My Message"); or new Status(ConstantStatus.OK, "My Message", null);} instead like this:
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 *  Status.getStatus(int code, String message);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 */
	@Deprecated
	public static Status getStatus(int code, String message) {
		return new Status();
	}

	/**
	 * @deprecated reason this method is deprecated </br>
	 * {will be removed in next version} </br>
	 * use {@link new Status(ConstantStatus.OK, "My Message"); or new Status(ConstantStatus.OK, "My Message", null);} instead like this:
	 * 
	 * <blockquote>
	 * 
	 * <pre>
	 * Status.getStatus();
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 */
	@Deprecated
	public static Status getStatus() {
		return new Status();
	}

	public int getApiCode() {
		return apiCode;
	}

	public void setApiCode(int apiCode) {
		this.apiCode = apiCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ExceptionWrapper> getExceptionWrappers() {
		return exceptionWrappers;
	}

	public void setExceptionWrappers(List<ExceptionWrapper> exceptionWrappers) {
		this.exceptionWrappers = exceptionWrappers;
	}

	/**
	 * @return
	 * @author CCJoshi
	 * @ModifiedBy CCJoshi
	 * @ModifiedDate May 10, 2017
	 */
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * @param apiCode
	 * @param message
	 * @param data
	 * @author KaushalBaria
	 * @ModifiedBy KaushalBaria
	 * @ModifiedDate April 27, 2018
	 */
	public Status(ConstantStatus status, String message, Object data) {
		this.apiCode = status.value();
		this.message = message;
		this.data = data;
	}

}
