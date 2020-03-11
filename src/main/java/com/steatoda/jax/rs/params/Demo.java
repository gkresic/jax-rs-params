package com.steatoda.jax.rs.params;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

	public static void main(String[] args) throws Exception {

		Log.info("Welcome to fields demo!");

		XmlConfiguration jettyConf = new XmlConfiguration(Resource.newResource(Thread.currentThread().getContextClassLoader().getResource("jetty.xml")));
		Server server = Server.class.cast(jettyConf.configure());
		// have to add ServletContextListener here (instead in web.xml) because otherwise it won't see our LazarusInjector as parent
		Handler handler = server.getHandler();
		while (handler != null && !WebAppContext.class.isInstance(handler) && HandlerWrapper.class.isInstance(handler))
			handler = HandlerWrapper.class.cast(handler).getHandler();
		if (handler == null)
			throw new RuntimeException("No WebAppContext handler configured in Jetty?!");
		WebAppContext.class.cast(handler).addEventListener(DemoInjector.get().getInstance(DemoGuiceResteasyBootstrapServletContextListener.class));

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					server.stop();
				} catch (Exception e) {
					Log.error("Unable to stop server", e);
				}
				server.destroy();
			}
		});

		server.start();

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			throw new RuntimeException("Main thread interrupted?!", e);
		}

		Log.info("Bye...");

	}
	
	private static final Logger Log = LoggerFactory.getLogger(Demo.class);
	
}
