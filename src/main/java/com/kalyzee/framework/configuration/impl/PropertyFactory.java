package com.kalyzee.framework.configuration.impl;

import com.kalyzee.framework.configuration.api.IProperty;
import com.kalyzee.framework.configuration.api.IPropertyFactory;

public class PropertyFactory implements IPropertyFactory {

	private static IPropertyFactory instance;
	
	public IProperty createValuedProperty(String name, String value) {
		ValuedProperty vp = new ValuedProperty();
		vp.setName(name);
		vp.setValue(value);
		return vp;
	}

	public IProperty createReferencedProperty(String name, String reference) {
		ReferencedProperty rp = new ReferencedProperty();
		rp.setName(name);
		rp.setReference(reference);
		return rp;
	}

	public static IPropertyFactory getInstance(){
		if (instance == null) {
			instance = new PropertyFactory();
		}
		return instance;
	}
}
