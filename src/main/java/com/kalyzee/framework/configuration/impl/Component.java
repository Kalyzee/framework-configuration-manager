package com.kalyzee.framework.configuration.impl;

import java.util.HashMap;

import com.kalyzee.framework.configuration.api.IComponent;
import com.kalyzee.framework.configuration.api.IProperty;

public class Component implements IComponent{

	private String reference;
	private String classname;
	private HashMap<String, IProperty> properties = new HashMap<String, IProperty>();
	
	public String getReference() {
		return reference;
	}

	public String getClassName() {
		return classname;
	}

	public void addProperty(IProperty property) {
		properties.put(property.getName(), property);
	}

	public IProperty getProperty(String reference) {
		return properties.get(reference);
	}

	public IProperty[] getProperties() {
		return properties.values().toArray(new IProperty[properties.values().size()]);
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setClassName(String className) {
		this.classname = className;
	}

}
