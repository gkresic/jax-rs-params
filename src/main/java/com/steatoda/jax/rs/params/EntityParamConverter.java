package com.steatoda.jax.rs.params;

import javax.ws.rs.ext.ParamConverter;

public class EntityParamConverter<T extends Enum<T>> implements ParamConverter<Entity<T>> {

	public EntityParamConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public final Entity<T> fromString(String value) {
		Entity<T> entity = new Entity<>();
		entity.setValue(Enum.valueOf(clazz, value));
		return entity;
	}
	
	@Override
	public final String toString(Entity<T> entity) {
		return entity.toString();
	}

	private final Class<T> clazz;
	
}
