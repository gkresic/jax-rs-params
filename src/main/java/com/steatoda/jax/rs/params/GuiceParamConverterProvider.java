package com.steatoda.jax.rs.params;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.util.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GuiceParamConverterProvider implements ParamConverterProvider {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType, final Type genericType, final Annotation[] annotations) {

		if (rawType.equals(Entity.class)) {

			Class<?> clazz = Types.getTypeArgument(genericType);
			
			if (clazz != null) {
				Log.info("genericType is: {}", clazz);
			} else {
				// https://issues.redhat.com/browse/RESTEASY-2484
				Log.warn("genericType is NULL!");
				clazz = Entity.Abc.class;
			}
			
			return new EntityParamConverter(clazz);

		}

		return null;
		
	}

	private static final Logger Log = LoggerFactory.getLogger(GuiceParamConverterProvider.class);

}
