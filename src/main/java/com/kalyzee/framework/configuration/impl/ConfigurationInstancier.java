package com.kalyzee.framework.configuration.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.kalyzee.framework.configuration.api.ConfigurationInstancierException;
import com.kalyzee.framework.configuration.api.ConfigurationLoaderException;
import com.kalyzee.framework.configuration.api.IComponent;
import com.kalyzee.framework.configuration.api.IConfigurationInstancier;
import com.kalyzee.framework.configuration.api.IProperty;
import com.kalyzee.framework.configuration.api.IReferencedProperty;
import com.kalyzee.framework.configuration.api.IValuedProperty;
import com.kalyzee.framework.servicelocator.api.IServiceLocator;
import com.kalyzee.framework.servicelocator.api.ServiceLocatorException;

public class ConfigurationInstancier implements IConfigurationInstancier {

	private IServiceLocator serviceLocator;

	public ConfigurationInstancier() {

	}

	public ConfigurationInstancier(IServiceLocator serviceLocator) {
		setServiceLocator(serviceLocator);
	}

	private String getSetterMethod(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen + 3).append("set")
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	public Object instanciate(IComponent component)
			throws ConfigurationInstancierException {
		Object o;
		try {
			o = doInstanciation(component.getClassName());
			IProperty[] properties = component.getProperties();
			for (int j = 0; j < properties.length; j++) {
				IProperty property = properties[j];
				String propertyName = property.getName();
				Class<?> propertyType = o.getClass()
						.getDeclaredField(propertyName).getType();
				Method m = o.getClass().getMethod(
						getSetterMethod(propertyName), propertyType);

				if (property instanceof IValuedProperty) {
					String propertyValue = ((IValuedProperty) property).getValue();

					Object castedPropertyValue = null;
					if (propertyType.isPrimitive()) {
						if (propertyType.equals(Integer.TYPE)) {
							castedPropertyValue = Integer
									.parseInt(propertyValue);
						} else {
							if (propertyType.equals(Double.TYPE)) {
								castedPropertyValue = Double
										.parseDouble(propertyValue);
							} else {
								if (propertyType.equals(Boolean.TYPE)) {
									castedPropertyValue = Boolean
											.valueOf(propertyValue);
								} else {
									if (propertyValue.equals(Float.TYPE)) {
										castedPropertyValue = Float
												.valueOf(propertyValue);
									}

								}

							}
						}

					} else {
						if (propertyType.equals(String.class)) {
							castedPropertyValue = propertyValue;
						} else {
							throw new ConfigurationLoaderException(
									"Can't set the type " + propertyType);
						}
					}
					m.invoke(o, castedPropertyValue);
				} else {
					if (property instanceof IReferencedProperty) {
						
						m.invoke(o, serviceLocator.lookup(((IReferencedProperty) property).getReference()));
								
										

					} else {
						throw new ConfigurationInstancierException(propertyName
								+ "must define ref ou value");
					}
				}
			}
		} catch (InstantiationException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (SecurityException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (NoSuchFieldException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (ServiceLocatorException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (ConfigurationLoaderException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new ConfigurationInstancierException(e.getMessage());
		}
		return o;
	}

	private Object doInstanciation(String classname)
			throws InstantiationException {

		try {
			Class<?> clazz;
			clazz = Class.forName(classname);
			return (Object) clazz.newInstance();
		} catch (IllegalArgumentException e) {
			throw new InstantiationException();

		} catch (InstantiationException e) {
			throw new InstantiationException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new InstantiationException(e.getMessage());
		} catch (SecurityException e) {
			throw new InstantiationException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new InstantiationException(e.getMessage());
		}
	}

	public void setServiceLocator(IServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;

	}

}
