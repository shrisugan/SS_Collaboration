package com.niit.collaboration.model;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue
	private int eventid;
	
	private String eventname;
	private String eventcategory;
	private String eventdatails;
	
	
	private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());


	public int getEventid() {
		return eventid;
	}


	public void setEventid(int eventid) {
		this.eventid = eventid;
	}


	public String getEventname() {
		return eventname;
	}


	public void setEventname(String eventname) {
		this.eventname = eventname;
	}


	public String getEventcategory() {
		return eventcategory;
	}


	public void setEventcategory(String eventcategory) {
		this.eventcategory = eventcategory;
	}


	public String getEventdatails() {
		return eventdatails;
	}


	public void setEventdatails(String eventdatails) {
		this.eventdatails = eventdatails;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
