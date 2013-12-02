package com.kalyzee.framework.configuration.impl;

import com.kalyzee.framework.configuration.api.IValuedProperty;

public class ValuedProperty implements IValuedProperty{
	
	private String name;
	private String value;
	
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		
	}

	public void setName(String name) {
		this.name = name;
	}

}
