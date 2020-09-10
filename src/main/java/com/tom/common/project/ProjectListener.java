package com.tom.common.project;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProjectListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// NOTHING TO BE DONE
	}

	@Override
	public void contextInitialized(ServletContextEvent sct) {
		new ProjectProperties();
	}
}
