package edu.asu.momo.core;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Teams consist of team members and managers. Managers can see the time sheets
 * of all the team members.
 * 
 * @author Julia Damerow
 *
 */
@Entity
public class Team {

	@Id
	private String id;
	private String name;
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<String> members;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<String> managers;
	
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
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}
	public List<String> getManagers() {
		return managers;
	}
	public void setManagers(List<String> managers) {
		this.managers = managers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
