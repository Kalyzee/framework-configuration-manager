package com.kalyzee.framework.configuration.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.kalyzee.framework.configuration.api.ConfigurationParserException;
import com.kalyzee.framework.configuration.api.IComponent;
import com.kalyzee.framework.configuration.api.IConfigurationParser;
import com.kalyzee.framework.configuration.api.IProperty;

public class ConfigurationParser implements IConfigurationParser {

	public ConfigurationParser(){
		
	}
	
	public IComponent[] parseComponents(String filePath)
			throws ConfigurationParserException {
		
		List<IComponent> components = new ArrayList<IComponent>(); 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document;

			document = db.parse(new FileInputStream(new File(filePath)));

			NodeList nodeList = document.getDocumentElement()
					.getElementsByTagName("component");
		
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				IComponent component = new Component();
				component.setClassName(node.getAttributes().getNamedItem("type").getNodeValue());
				component.setReference(node.getAttributes().getNamedItem("name").getNodeValue());
			 

				if (node.hasChildNodes()) {
					NodeList propertiesNodeList = ((Element) node)
							.getElementsByTagName("property");
					for (int j = 0; j < propertiesNodeList.getLength(); j++) {
						String propertyName = propertiesNodeList.item(j)
								.getAttributes().getNamedItem("name")
								.getNodeValue();
						Node propertyValueNode = propertiesNodeList.item(j)
								.getAttributes().getNamedItem("value");
						IProperty property = null;
						if (propertyValueNode != null) {
							property = PropertyFactory.getInstance().createValuedProperty(propertyName, propertyValueNode.getNodeValue());
						} else {
							Node nodeRef = propertiesNodeList.item(j)
									.getAttributes().getNamedItem("ref");
							if (!nodeRef.equals(null)) {
								property = PropertyFactory.getInstance().createReferencedProperty(propertyName, nodeRef.getNodeValue());
								
							} else {
								throw new ConfigurationParserException(
										propertyName
												+ "must define ref ou value");
							}
						
						}
						
						component.addProperty(property);

					}
					components.add(component);
				}
			}
			return components.toArray(new IComponent[components.size()]);
		} catch (FileNotFoundException e) {
			throw new ConfigurationParserException(e.getMessage());
		} catch (SAXException e) {
			throw new ConfigurationParserException(e.getMessage());
		} catch (IOException e) {
			throw new ConfigurationParserException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new ConfigurationParserException(e.getMessage());
		}

	}

}
