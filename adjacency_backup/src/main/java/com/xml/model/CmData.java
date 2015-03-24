package com.xml.model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CmData {
	
	
	private Header header; 
	private List<ManagedObject> managedObject;

	public List<ManagedObject> getManagedObject() {
		return managedObject;
	}

	public void setManagedObject(List<ManagedObject> managedObject) {
		this.managedObject = managedObject;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	
	 @XmlAttribute
		private String type;
		public String getType() {
			return type;
		}

		public String getScope() {
			return scope;
		}

		public String getName() {
			return name;
		}

		public String getId() {
			return id;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setId(String id) {
			this.id = id;
		}


		@XmlAttribute
		private String scope;
		 @XmlAttribute
		private String name;
		@XmlAttribute
		private String id;

}
