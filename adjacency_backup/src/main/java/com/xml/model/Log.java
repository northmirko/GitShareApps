package com.xml.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Log {
	
    @XmlAttribute
	private String dateTime;
    
    public String getDateTime() {
		return dateTime;
	}


	public String getAction() {
		return action;
	}


	public String getAppInfo() {
		return appInfo;
	}


	public String getValue() {
		return value;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}


	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute
	private String action;
	@XmlAttribute
    private String appInfo;
    

    @XmlValue
	private String value;
	
}
