package com.kalyzee.framework.configuration.api;

import com.kalyzee.framework.servicelocator.api.ServiceLocatorException;


public interface IConfigurationLoaderFactory {

	IConfigurationLoader createConfigurationLoader() throws ServiceLocatorException;
	
}
