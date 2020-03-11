package com.steatoda.jax.rs.params.resources;

import java.util.Objects;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.steatoda.jax.rs.params.Entity;

@Path("foo")
public class FooResource {

	@Inject
	public FooResource(BarResource.Factory barResourceFactory) {
		this.barResourceFactory = barResourceFactory;
	}
	
	@GET
	@Transactional
	public String get(@QueryParam("entity") Entity<Entity.Abc> abc) {
		return Objects.toString(abc, "Set 'entity' param");
	}

	@Path("bar")
	public BarResource bar() {

		return barResourceFactory.create();

	}

	private final BarResource.Factory barResourceFactory;

}