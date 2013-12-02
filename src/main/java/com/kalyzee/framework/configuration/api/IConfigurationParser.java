package com.kalyzee.framework.configuration.api;


public interface IConfigurationParser {
	IComponent[] parseComponents(String fileName) throws ConfigurationParserException;
}
