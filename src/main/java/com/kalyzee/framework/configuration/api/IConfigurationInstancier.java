package com.kalyzee.framework.configuration.api;

import com.kalyzee.framework.servicelocator.api.IServiceLocator;

public interface IConfigurationInstancier {

	Object instanciate(IComponent component) throws ConfigurationInstancierException;
	void setServiceLocator(IServiceLocator serviceLocator);
}
