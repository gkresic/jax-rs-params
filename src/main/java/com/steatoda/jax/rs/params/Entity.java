package com.steatoda.jax.rs.params;

import java.util.Objects;

public class Entity<T extends Enum<T>> {

	public static enum Abc {
		a,
		b,
		c
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Objects.toString(value, "<NULL>");
	}

	private T value;

}
