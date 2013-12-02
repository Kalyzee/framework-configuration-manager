package com.kalyzee.framework.configuration.impl;

import com.kalyzee.framework.configuration.api.IConfigurationLoader;
import com.kalyzee.framework.configuration.api.IConfigurationLoaderFactory;
import com.kalyzee.framework.servicelocator.api.ServiceLocatorException;
import com.kalyzee.framework.servicelocator.impl.ServiceLocatorFactory;


public class ConfigurationLoaderFactory implements IConfigurationLoaderFactory{
	
	private static IConfigurationLoaderFactory instance;
	

	public static IConfigurationLoaderFactory getInstance(){
		if (instance == null){
			instance = new ConfigurationLoaderFactory();
		}
		return instance;
	}

	public IConfigurationLoader createConfigurationLoader() throws ServiceLocatorException {
		return new ConfigurationLoader(ServiceLocatorFactory.getInstance().getServiceLocator());
		
	}
}
