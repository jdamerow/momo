package edu.asu.momo.web.teams.backing;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.core.Team;
import edu.asu.momo.web.user.backing.UserBackingBean;

/**
 * Backing Bean for {@link Team} objects.
 * 
 * @author Julia Damerow
 *
 */
public class TeamBackingBean {

	@NotEmpty(message = "Please provide a team name.")
	private String name;
	private String description;
	private String id;
	private boolean isTeamManager;
	
	private List<UserBackingBean> members;
	private List<UserBackingBean> managers;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserBackingBean> getMembers() {
		return members;
	}
	public void setMembers(List<UserBackingBean> members) {
		this.members = members;
	}
	public List<UserBackingBean> getManagers() {
		return managers;
	}
	public void setManagers(List<UserBackingBean> managers) {
		this.managers = managers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getIsTeamManager() {
		return isTeamManager;
	}
	public void setIsTeamManager(boolean isTeamManager) {
		this.isTeamManager = isTeamManager;
	}
	
}
