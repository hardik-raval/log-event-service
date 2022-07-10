package com.logevents.demo.domain;

public class LogEventVO {
	private String id;
	private String state;
	private String type;
	private String host;
	private String timestamp;
	
	public LogEventVO(){}
	
	public LogEventVO(String id, String state, String type, String host, String timestamp) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "id:" + this.id + ", state: " + this.state + ", timestamp: " + this.timestamp;
	}
}
