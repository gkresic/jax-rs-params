package com.steatoda.jax.rs.params.transactional;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class TransactionInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

		// no-op (for demo purposes)

		return methodInvocation.proceed();
		
	}

}

