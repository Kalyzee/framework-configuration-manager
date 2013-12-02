package com.kalyzee.framework.configuration.impl;

import com.kalyzee.framework.configuration.api.IReferencedProperty;

public class ReferencedProperty implements IReferencedProperty{

	private String name;
	private String reference;
	
	
	public String getName() {
		return name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference  = reference;
		
	}

	public void setName(String name) {
		this.name = name;
	} 

}
