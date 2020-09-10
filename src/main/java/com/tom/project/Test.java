package com.tom.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tom.common.beans.ConstantStatus;
import com.tom.common.beans.ObjectMapper;
import com.tom.common.beans.Status;
import com.tom.common.exception.TOMRuntimeException;
import com.tom.util.JsonUtil;

public class Test {
	public static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	public static void main(String[] args) {

		try {
			int c = 1/0;
			System.out.println(c);
		} catch(Exception e) {
			LOGGER.error("Error in createProject() method :: ", e);
			
		}

	}

}
