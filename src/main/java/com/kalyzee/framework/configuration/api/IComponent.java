package com.kalyzee.framework.configuration.api;

public interface IComponent {
	String getReference();
	void setReference(String reference);
	String getClassName();
	void setClassName(String className);
	void addProperty(IProperty property);
	IProperty getProperty(String name);
	IProperty[] getProperties();
	
	
}
