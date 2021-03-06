package edu.asu.momo.core;

import java.util.Date;

public class TimeEntry {

	private Date startDate;
	private Date endDate;
	private String projectId;
	private String notes;
	private String clockingInNotes;
	private String id;
	private String userId;
	private float breakTime;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public float getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(float breakTime) {
		this.breakTime = breakTime;
	}
	public String getClockingInNotes() {
		return clockingInNotes;
	}
	public void setClockingInNotes(String clockingInNotes) {
		this.clockingInNotes = clockingInNotes;
	}

}
