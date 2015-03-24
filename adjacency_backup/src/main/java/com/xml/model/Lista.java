package com.xml.model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="List")
public class Lista {
	
	@XmlAttribute
	private String name;
	
	public String getName() {
		return name;
	}

	
	
	@XmlElement
	private List<Item> item;
	
	public List<Item> getItem() {
		return item;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setItems(List<Item> item) {
		this.item = item;
	}

	
	
	@XmlElement
	private List<P> p;

	public List<P> getP() {
		return p;
	}

	public void setP(List<P> p) {
		this.p = p;
	}
	

	
	
	

}
