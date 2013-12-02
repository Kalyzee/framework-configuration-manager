package com.kalyzee.framework.configuration.impl;

import com.kalyzee.framework.configuration.api.ConfigurationInstancierException;
import com.kalyzee.framework.configuration.api.ConfigurationLoaderException;
import com.kalyzee.framework.configuration.api.ConfigurationParserException;
import com.kalyzee.framework.configuration.api.IComponent;
import com.kalyzee.framework.configuration.api.IConfigurationInstancier;
import com.kalyzee.framework.configuration.api.IConfigurationLoader;
import com.kalyzee.framework.configuration.api.IConfigurationParser;
import com.kalyzee.framework.servicelocator.api.IServiceLocator;
import com.kalyzee.framework.servicelocator.api.ServiceLocatorException;



public class ConfigurationLoader implements IConfigurationLoader {

	private IServiceLocator serviceLocator;
	private IConfigurationParser configurationParser;
	private IConfigurationInstancier configurationInstancier;
	
	
	public ConfigurationLoader(IServiceLocator serviceLocator){
		setServiceLocator(serviceLocator);
		configurationInstancier = new ConfigurationInstancier(serviceLocator);
		configurationParser = new ConfigurationParser();
	}

	public void loadFromFile(String filePath) throws ConfigurationLoaderException {
		try {
			IComponent[] components = configurationParser.parseComponents(filePath);
			for(IComponent component: components){
				Object o = configurationInstancier.instanciate(component);
				serviceLocator.bind(component.getReference(), o);
			}
		} catch (ConfigurationParserException e) {
			throw new ConfigurationLoaderException(e.getMessage());
		} catch (ConfigurationInstancierException e) {
			throw new ConfigurationLoaderException(e.getMessage());			
		} catch (ServiceLocatorException e) {
			throw new ConfigurationLoaderException(e.getMessage());	
		}
	}


	public IServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}


	public IConfigurationParser getConfigurationParser() {
		return configurationParser;
	}


	public void setConfigurationParser(ConfigurationParser configurationParser) {
		this.configurationParser = configurationParser;
	}


	public IConfigurationInstancier getConfigurationInstancier() {
		return configurationInstancier;
	}


	public void setConfigurationInstancier(IConfigurationInstancier configurationInstancier) {
		this.configurationInstancier = configurationInstancier;
	}

}
