package edu.asu.momo.web.projects.backing;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.web.teams.backing.TeamBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

/**
 * 
 * @author Julia Damerow
 *
 */
public class ProjectBackingBean {

	private String id;
	
	@NotEmpty
	private String name;
	private String description;
	private List<UserBackingBean> members;
	private TeamBackingBean team;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public TeamBackingBean getTeam() {
		return team;
	}
	public void setTeam(TeamBackingBean team) {
		this.team = team;
	}
	
	
}
