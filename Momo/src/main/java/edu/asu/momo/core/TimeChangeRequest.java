package edu.asu.momo.core;

import java.util.Date;

/**
 * This class encapsulates a time change request that needs approval
 * from a team manager.
 * 
 * @author Julia Damerow
 *
 */
public class TimeChangeRequest extends TimeRequest {

	private Date oldStartDate;
	private Date oldEndDate;
	private Date newStartDate;
	private Date newEndDate;
	private Date makeupStartDate;
	private Date makeupEndDate;
	private Date reviewStartDate;
	private Date reviewEndDate;

	
	public Date getOldStartDate() {
		return oldStartDate;
	}
	public void setOldStartDate(Date oldStartDate) {
		this.oldStartDate = oldStartDate;
	}
	public Date getOldEndDate() {
		return oldEndDate;
	}
	public void setOldEndDate(Date oldEndDate) {
		this.oldEndDate = oldEndDate;
	}
	public Date getNewStartDate() {
		return newStartDate;
	}
	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}
	public Date getNewEndDate() {
		return newEndDate;
	}
	public void setNewEndDate(Date newEndDate) {
		this.newEndDate = newEndDate;
	}
	public Date getReviewStartDate() {
		return reviewStartDate;
	}
	public void setReviewStartDate(Date reviewStartDate) {
		this.reviewStartDate = reviewStartDate;
	}
	public Date getReviewEndDate() {
		return reviewEndDate;
	}
	public void setReviewEndDate(Date reviewEndDate) {
		this.reviewEndDate = reviewEndDate;
	}
	public Date getMakeupStartDate() {
		return makeupStartDate;
	}
	public void setMakeupStartDate(Date makeupStartDate) {
		this.makeupStartDate = makeupStartDate;
	}
	public Date getMakeupEndDate() {
		return makeupEndDate;
	}
	public void setMakeupEndDate(Date makeupEndDate) {
		this.makeupEndDate = makeupEndDate;
	}
	
	
}
