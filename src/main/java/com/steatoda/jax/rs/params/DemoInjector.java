package com.steatoda.jax.rs.params;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.steatoda.jax.rs.params.transactional.DbModule;

public class DemoInjector {

	public static Injector get() {
		if (instance == null)
			instance = new DemoInjector();
		return instance.getInjector();
	}
	
	private DemoInjector() {
		injector = Guice.createInjector(
			new DbModule(),
			new DemoServletModule()
		);
	}

	public Injector getInjector() { return injector; }

	private static DemoInjector instance = null;
	private final Injector injector;

}
