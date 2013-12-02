package com.kalyzee.framework.configuration.api;

public interface IPropertyFactory {

	IProperty createValuedProperty(String name, String value);
	IProperty createReferencedProperty(String name, String reference);
	
}
