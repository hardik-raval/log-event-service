package com.logevents.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogEvent {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventId;
    private String eventDuration;
    private String type;
    private String host;
    private String alert;
    
    public LogEvent() {}

    public LogEvent(String eventId, String eventDuration, String type, String host, String alert) {
		this.eventId = eventId;
		this.eventDuration = eventDuration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventDuration() {
		return eventDuration;
	}
	public void setEventDuration(String eventDuration) {
		this.eventDuration = eventDuration;
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
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
}
