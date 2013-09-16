package edu.asu.momo.web.recording.backing;

import org.hibernate.validator.constraints.NotEmpty;

public class TimePeriod {

	@NotEmpty(message = "Please provide start date.")
	private String startDay;
	@NotEmpty(message = "Please provide end date.")
	private String endDay;
	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

}
