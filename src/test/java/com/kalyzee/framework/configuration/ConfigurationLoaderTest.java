package com.kalyzee.framework.configuration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.kalyzee.framework.configuration.api.ConfigurationLoaderException;
import com.kalyzee.framework.configuration.api.IConfigurationLoader;
import com.kalyzee.framework.configuration.impl.ConfigurationLoader;
import com.kalyzee.framework.servicelocator.api.IServiceLocator;
import com.kalyzee.framework.servicelocator.api.ServiceLocatorException;
import com.kalyzee.framework.servicelocator.impl.ServiceLocatorFactory;


public class ConfigurationLoaderTest {

	IConfigurationLoader ic;
	IServiceLocator sl;
	@Before
	public void before() throws ServiceLocatorException, ConfigurationLoaderException{
		sl = ServiceLocatorFactory.getInstance().getServiceLocator();
		ic = new ConfigurationLoader(sl);
		ic.loadFromFile(getClass().getResource("/config/modules-fr.xml").getFile());
	}
	
	
	
	@Test
	public void setterInjectionTest() throws ServiceLocatorException, ConfigurationLoaderException{
		TestingWithRef t = (TestingWithRef) sl.lookup("kalyzee/testing");
		Assert.assertNotNull(t);
		Assert.assertEquals("Salut",t.getString());
	}

	@Test
	public void refInjectionTest() throws ServiceLocatorException, ConfigurationLoaderException{
		TestingWithRefAndPrimitivesTypes t = (TestingWithRefAndPrimitivesTypes) sl.lookup("kalyzee/testingWithRef");
		Assert.assertNotNull(t);
		Assert.assertEquals("Salut",t.getString());
		Assert.assertTrue(t.isBool());
		Assert.assertEquals(2,t.getInteger());
		TestingWithRef testing = (TestingWithRef) sl.lookup("kalyzee/testing");
		Assert.assertNotNull(testing);
		Assert.assertEquals(testing,t.getTesting());
		Assert.assertEquals("Salut",t.getTesting().getString());
		
	}
	
}
