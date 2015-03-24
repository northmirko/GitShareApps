package com.xml.model;




import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Raml {
	
	
	private CmData cmData; 
	
	
	
	 @XmlAttribute
private String version;
	 @XmlAttribute
private String xmlns;
public String getXmlns() {
	return xmlns;
}
public void setXmlns(String xmlns) {
	this.xmlns = xmlns;
}
public String getVersion() {
	return version;
}
public void setVersion(String version) {
	this.version = version;
}
public CmData getCmData() {
	return cmData;
}
public void setCmData(CmData cmData) {
	this.cmData = cmData;
}

}
