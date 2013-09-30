package edu.asu.momo.web.request.backing;

public class TimeChangeRequestBean {

	private String shiftDay;
	private String shiftStart;
	private String shiftEnd;
	
	private int workThatDay;
	private String newShiftStart;
	private String newShiftEnd;
	
	public String getShiftDay() {
		return shiftDay;
	}
	public void setShiftDay(String shiftDay) {
		this.shiftDay = shiftDay;
	}
	public String getShiftStart() {
		return shiftStart;
	}
	public void setShiftStart(String shiftStart) {
		this.shiftStart = shiftStart;
	}
	public String getShiftEnd() {
		return shiftEnd;
	}
	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd = shiftEnd;
	}
	public int getWorkThatDay() {
		return workThatDay;
	}
	public void setWorkThatDay(int workThatDay) {
		this.workThatDay = workThatDay;
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
