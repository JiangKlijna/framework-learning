package com.jiangKlijna.web.app;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextWrapper {
	public WebApplicationContext getWebApplicationContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}

	public ServletContext getServletContext() {
		return getWebApplicationContext().getServletContext();
	}

	public ApplicationContext getApplicationContext() {
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
}
