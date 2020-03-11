package com.steatoda.jax.rs.params.resources;

import java.util.Objects;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.steatoda.jax.rs.params.Entity;

@Path("bar")
public class BarResource {

	public static interface Factory {
		BarResource create();
	}
	
	@GET
	@Transactional
	public String get(@QueryParam("entity") Entity<Entity.Abc> abc) {
		return Objects.toString(abc, "Set 'entity' param");
	}

}