package com.tom.common.beans;

public class QueryParams {

	public enum QueryConstants {
		LIKE, STARTS_WITH,ENDS_WITH, GT, LT, GE, LE, IN, NOT_IN, EQ, NE, BETWEEN, ORDER_BY, RESULT_COUNT, DESC, ASC,ISNULL,NOTNULL;
	}

	private Object propertyName;
	private Object condition;
	private Object propertyValue;

	public QueryParams(Object propertyName, Object condition, Object propertyValue) {

		this.propertyName = propertyName;
		this.condition = condition;
		this.propertyValue = propertyValue;
	}

	public Object getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(Object propertyName) {
		this.propertyName = propertyName;
	}

	public Object getCondition() {
		return condition;
	}

	public void setCondition(Object condition) {
		this.condition = condition;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}

}
