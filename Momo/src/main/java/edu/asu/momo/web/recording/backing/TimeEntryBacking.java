package edu.asu.momo.web.recording.backing;

import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

public class TimeEntryBacking {
	
	private UserBackingBean user;
	private String date;
	private long dateAsMSec;
	
	private String startDate;
	private long startTime;
	
	private String endDate;
	private long endTime;
	
	private String time;
	private ProjectBackingBean project;
	private String notes;
	
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
	public ProjectBackingBean getProject() {
		return project;
	}
	public void setProject(ProjectBackingBean project) {
		this.project = project;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getDateAsMSec() {
		return dateAsMSec;
	}
	public void setDateAsMSec(long dateAsMSec) {
		this.dateAsMSec = dateAsMSec;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
