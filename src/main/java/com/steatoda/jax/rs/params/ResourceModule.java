package com.steatoda.jax.rs.params;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.steatoda.jax.rs.params.resources.BarResource;
import com.steatoda.jax.rs.params.resources.FooResource;

public class ResourceModule extends AbstractModule {

	@Override 
	protected void configure() {

		bind(GuiceParamConverterProvider.class);

		bind(FooResource.class);
		bind(BarResource.class);

		install(new FactoryModuleBuilder().build(BarResource.Factory.class));

	}

}
