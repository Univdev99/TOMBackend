package com.tom.common.beans;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
	private static ObjectMapper objectMapper;

	private ObjectMapper() {
		super();
		this.disable(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS);
		this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		this.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}
}
