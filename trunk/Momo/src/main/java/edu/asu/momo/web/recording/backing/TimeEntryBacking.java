package edu.asu.momo.web.recording.backing;

import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

public class TimeEntryBacking {
	
	private String id;
	private UserBackingBean user;
	private String date;
	private long dateAsMSec;
	
	private String startDate;
	private long startTime;
	
	private String endDate;
	private long endTime;
	
	private String time;
	private String timeInHM;
	private ProjectBackingBean project;
	private String notes;
	private String clockingInNotes;
	
	private String breakTime;
	
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
	public String getTimeInHM() {
		return timeInHM;
	}
	public void setTimeInHM(String timeInHM) {
		this.timeInHM = timeInHM;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClockingInNotes() {
		return clockingInNotes;
	}
	public void setClockingInNotes(String clockingInNotes) {
		this.clockingInNotes = clockingInNotes;
	}
	
	

}
