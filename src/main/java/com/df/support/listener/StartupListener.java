package com.df.support.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent scEvent) {
	}

	@Override
	public void contextInitialized(ServletContextEvent scEvent) {
		ServletContext context = scEvent.getServletContext();
		String ctx = context.getContextPath();
		context.setAttribute("ctx", ctx);
	}

}
