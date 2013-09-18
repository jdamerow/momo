package edu.asu.momo.web.timesheets.backing;

import java.util.List;

import edu.asu.momo.web.user.backing.UserBackingBean;


public class TimeSheetSelection {

	private String startDay;
	private String endDay;
	
	private String teamId;
	
	private List<UserBackingBean> managers;
	private List<UserBackingBean> members;
	
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
	public List<UserBackingBean> getManagers() {
		return managers;
	}
	public void setManagers(List<UserBackingBean> managers) {
		this.managers = managers;
	}
	public List<UserBackingBean> getMembers() {
		return members;
	}
	public void setMembers(List<UserBackingBean> members) {
		this.members = members;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
