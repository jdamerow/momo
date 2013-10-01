package edu.asu.momo.web.request.backing;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.valid.DateDayCheck;
import edu.asu.momo.valid.DateTimeCheck;

public class TimeRequestBean {

	@NotEmpty
	@DateDayCheck
	private String shiftDay;
	@NotEmpty
	@DateTimeCheck
	private String shiftStart;
	@NotEmpty
	@DateTimeCheck
	private String shiftEnd;

	public TimeRequestBean() {
		super();
	}

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

}