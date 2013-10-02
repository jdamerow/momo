package edu.asu.momo.web.request.backing;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.valid.DateDayCheck;
import edu.asu.momo.valid.DateTimeCheck;
import edu.asu.momo.web.user.backing.UserBackingBean;

public class TimeRequestBean {

	@NotEmpty(message = "What day is your shift?")
	@DateDayCheck
	private String shiftDay;
	private long shiftDayMS;
	
	@NotEmpty(message = "When does your shift start?")
	@DateTimeCheck
	private String shiftStart;
	@NotEmpty(message = "When does your shift end?")
	@DateTimeCheck
	private String shiftEnd;
	
	private UserBackingBean requester;
	
	@NotEmpty(message = "We would like to know why you want to change your work times.")
	private String requestNotes;
	
	private String status;	
	private String id;
	private String requestedOn;
	private long requestedOnMS;
	private UserBackingBean reviewer;
	private String reviewedOn;
	private String reviewNotes;

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

	public String getRequestNotes() {
		return requestNotes;
	}

	public void setRequestNotes(String requestNotes) {
		this.requestNotes = requestNotes;
	}

	public UserBackingBean getRequester() {
		return requester;
	}

	public void setRequester(UserBackingBean requester) {
		this.requester = requester;
	}

	public long getShiftDayMS() {
		return shiftDayMS;
	}

	public void setShiftDayMS(long shiftDayMS) {
		this.shiftDayMS = shiftDayMS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestedOn() {
		return requestedOn;
	}

	public void setRequestedOn(String requstedOn) {
		this.requestedOn = requstedOn;
	}

	public long getRequestedOnMS() {
		return requestedOnMS;
	}

	public void setRequestedOnMS(long requestedOnMS) {
		this.requestedOnMS = requestedOnMS;
	}

	public UserBackingBean getReviewer() {
		return reviewer;
	}

	public void setReviewer(UserBackingBean reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewedOn() {
		return reviewedOn;
	}

	public void setReviewedOn(String reviewedOn) {
		this.reviewedOn = reviewedOn;
	}

	public String getReviewNotes() {
		return reviewNotes;
	}

	public void setReviewNotes(String reviewNotes) {
		this.reviewNotes = reviewNotes;
	}

}