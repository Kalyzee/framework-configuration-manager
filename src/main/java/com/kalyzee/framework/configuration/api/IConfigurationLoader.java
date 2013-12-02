package com.kalyzee.framework.configuration.api;

import com.kalyzee.framework.servicelocator.api.IServiceLocator;

public interface IConfigurationLoader {

	void loadFromFile(String filePath) throws ConfigurationLoaderException;
	
	void setServiceLocator(IServiceLocator sl);
	
}
