package com.steatoda.jax.rs.params;

import javax.inject.Singleton;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;

import com.google.inject.servlet.ServletModule;

public class DemoServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		
		super.configureServlets();
		
		bind(HttpServlet30Dispatcher.class).in(Singleton.class);
		serve("/*").with(HttpServlet30Dispatcher.class);

	}

}
