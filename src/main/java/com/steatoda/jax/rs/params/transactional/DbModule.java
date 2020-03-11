package com.steatoda.jax.rs.params.transactional;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matchers;

import javax.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

public class DbModule extends AbstractModule {

	public DbModule() {
		this.transactionInterceptor = new TransactionInterceptor();
	}

	@Override
	protected void configure() {

		Synthetic<Class<?>> syntheticClassMatcher = new Synthetic<>();
		Synthetic<AnnotatedElement> syntheticMemberMatcher = new Synthetic<>();

		bindInterceptor(
			Matchers.not(syntheticClassMatcher),
			Matchers.not(syntheticMemberMatcher).and(Matchers.annotatedWith(Transactional.class)),
			transactionInterceptor
		);
		
		bindInterceptor(
			Matchers.not(syntheticClassMatcher).and(Matchers.annotatedWith(Transactional.class)),
			Matchers.not(syntheticMemberMatcher),
			transactionInterceptor
		);

	}

	private static class Synthetic<T extends Object> extends AbstractMatcher<T> implements Serializable {
		public boolean matches(T t) {
			if (t instanceof Class)
				return Class.class.cast(t).isSynthetic();
			if (t instanceof Member)
				return Member.class.cast(t).isSynthetic();
			return false;
		}
		@Override
		public String toString() {
			return "synthetic()";
		}
		private static final long serialVersionUID = 0;
	}
	
	private final TransactionInterceptor transactionInterceptor;
	
}
