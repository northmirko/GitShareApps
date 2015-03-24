package com.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Item {
	
	
	@XmlElement
	private List<P> p;

	public List<P> getP() {
		return p;
	}

	public void setP(List<P> p) {
		this.p = p;
	}

}
