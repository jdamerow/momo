package edu.asu.momo.core;

/**
 * This class is the basis for all time (change) requests,
 * such as time change, time off, additional time, etc.
 * 
 * @author Julia Damerow
 *
 */
public abstract class TimeRequest {

	private String username;
	private String requestNotes;
	private String reviewedBy;
	private int status;
	private String reviewNotes;

	public TimeRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequestNotes() {
		return requestNotes;
	}

	public void setRequestNotes(String requestNotes) {
		this.requestNotes = requestNotes;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReviewNotes() {
		return reviewNotes;
	}

	public void setReviewNotes(String reviewNotes) {
		this.reviewNotes = reviewNotes;
	}

}