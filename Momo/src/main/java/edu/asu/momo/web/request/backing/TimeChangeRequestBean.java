package edu.asu.momo.web.request.backing;

import edu.asu.momo.valid.DateDayCheck;
import edu.asu.momo.valid.DateTimeCheck;
import edu.asu.momo.valid.GroupComplete;
import edu.asu.momo.valid.GroupCompleteCheck;

@GroupCompleteCheck(groupMessages= { "Please specify both, a new start and end time, for your work shift.", "Please specify date, start, and end time for the day you want to make up your hours." })
public class TimeChangeRequestBean extends TimeRequestBean {

	@DateTimeCheck
	@GroupComplete(groupId = 0)
	private String newShiftStart;
	@DateTimeCheck
	@GroupComplete(groupId = 0)
	private String newShiftEnd;
	
	@DateDayCheck
	@GroupComplete(groupId = 1, message = "Please specify a date.")
	private String makeupDay;
	@DateTimeCheck
	@GroupComplete(groupId = 1, message = "Please specify a start time.")
	private String makeupShiftStart;
	@DateTimeCheck
	@GroupComplete(groupId = 1, message = "Please specify an end time.")
	private String makeupShiftEnd;
	
	public String getMakeupDay() {
		return makeupDay;
	}
	public void setMakeupDay(String makeupDay) {
		this.makeupDay = makeupDay;
	}
	public String getMakeupShiftStart() {
		return makeupShiftStart;
	}
	public void setMakeupShiftStart(String makeupShiftStart) {
		this.makeupShiftStart = makeupShiftStart;
	}
	public String getMakeupShiftEnd() {
		return makeupShiftEnd;
	}
	public void setMakeupShiftEnd(String makeupShiftEnd) {
		this.makeupShiftEnd = makeupShiftEnd;
	}
	public String getNewShiftStart() {
		return newShiftStart;
	}
	public void setNewShiftStart(String newShiftStart) {
		this.newShiftStart = newShiftStart;
	}
	public String getNewShiftEnd() {
		return newShiftEnd;
	}
	public void setNewShiftEnd(String newShiftEnd) {
		this.newShiftEnd = newShiftEnd;
	}
	
	
}
