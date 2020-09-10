package com.tom.common.project;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectProperties {
	static final Logger LOGGER = LoggerFactory.getLogger(ProjectProperties.class);
	static Properties properties = new Properties();

	public ProjectProperties() {
		try {
			loadProperties();
		} catch (Exception e) {
			LOGGER.error("Error caught in ProjectProperties()", e);
		}
	}

	private void loadProperties() throws IOException {
		try (InputStream inputStream = ProjectProperties.class.getResourceAsStream("/project.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("Error in loadProperties", e);
		}
	}

	public static String get(String property) {
		return (String) properties.get(property);
	}

}
