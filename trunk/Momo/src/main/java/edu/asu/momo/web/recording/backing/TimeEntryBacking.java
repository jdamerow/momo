package edu.asu.momo.web.recording.backing;

import java.util.Date;

import edu.asu.momo.web.user.backing.UserBackingBean;

public class TimeEntryBacking {
	
	private UserBackingBean user;
	private String date;
	private String startDate;
	private String endDate;
	private String time;
	
	public UserBackingBean getUser() {
		return user;
	}
	public void setUser(UserBackingBean user) {
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
