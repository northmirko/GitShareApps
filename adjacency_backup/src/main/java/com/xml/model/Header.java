package com.xml.model;




import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Header {
	
	
	private Log log;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	

}
