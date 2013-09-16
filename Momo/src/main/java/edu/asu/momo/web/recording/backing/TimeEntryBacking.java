package edu.asu.momo.web.recording.backing;

import java.util.Date;

import edu.asu.momo.web.user.backing.UserForm;

public class TimeEntryBacking {
	
	private UserForm user;
	private String date;
	private String startDate;
	private String endDate;
	private String time;
	
	public UserForm getUser() {
		return user;
	}
	public void setUser(UserForm user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
