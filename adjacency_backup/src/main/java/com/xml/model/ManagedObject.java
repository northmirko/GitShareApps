package com.xml.model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ManagedObject {
	
	
	@XmlAttribute(name="class")
	private String class_atribute;
	public String getClass_atribute() {
		return class_atribute;
	}

	public String getVersion() {
		return version;
	}

	public String getDistName() {
		return distName;
	}

	public String getId() {
		return id;
	}

	public String getOperation() {
		return operation;
	}

	public void setClass_atribute(String class_atribute) {
		this.class_atribute = class_atribute;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	 @XmlAttribute
	private String version;
	 @XmlAttribute
	private String distName;
	 @XmlAttribute
	private String id;
	 @XmlAttribute
	private String operation;
	@XmlElement
	private List<P> p;

	public List<P> getP() {
		return p;
	}

	public void setP(List<P> p) {
		this.p = p;
	}
	@XmlElement
	private List<Lista> list;
	public List<Lista> getList() {
		return list;
	}

	public void setList(List<Lista> list) {
		this.list = list;
	}

}
