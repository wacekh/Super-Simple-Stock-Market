package wacekh.sssm.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import wacekh.sssm.web.rest.ResponseCorsFilter;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 * Setup configuration of servlet and stock module.
 * 
 * @author Waclaw Holub
 *
 */
public class ApplicationSetup extends GuiceServletContextListener {

	/*
	 * (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {

			/*
			 * (non-Javadoc)
			 * @see com.google.inject.servlet.ServletModule#configureServlets()
			 */
			@Override
			protected void configureServlets() {

				super.configureServlets();

				// Configuring Jersey via Guice:
				ResourceConfig resourceConfig = new PackagesResourceConfig("wacekh.sssm");
				for (Class<?> resource : resourceConfig.getClasses()) {
					bind(resource);
				}

				// hook Jackson into Jersey as the POJO <-> JSON mapper
				bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

				serve("/web/*").with(GuiceContainer.class);

				filter("/web/*").through(ResponseCorsFilter.class);
			}
		}, new StockModule());
	}
}
