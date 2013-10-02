package edu.asu.momo.teams.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.ITeamDBManager;
import edu.asu.momo.teams.ITeamsManager;

@Service
public class TeamsManager implements ITeamsManager {

	@Autowired
	private ITeamDBManager dbManager;
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.teams.impl.ITeamsManager#addTeam(edu.asu.momo.core.Team)
	 */
	@Override
	public boolean addTeam(Team team) {
		team.setId(UUID.randomUUID().toString());
		return dbManager.updateTeam(team);
	}

	/* (non-Javadoc)
	 * @see edu.asu.momo.teams.impl.ITeamsManager#getTeamsOfUser(java.lang.String)
	 */
	@Override
	public List<Team> getTeamsOfUser(String user) {
		return dbManager.getTeamsOfUser(user);
	}
	
	@Override
	public boolean updateTeam(Team team) {
		return dbManager.updateTeam(team);
	}
	
	@Override
	public Team getTeam(String id) {
		return dbManager.getTeam(id);
	}
	
	@Override
	public boolean deleteTeam(String id) {
		return dbManager.deleteTeam(id);
	}
	
	@Override
	public List<Team> getAllTeams() {
		return dbManager.getAllTeams();
	}
	
	@Override
	public List<Team> getManagedTeams(String manager) {
		List<Team> teams = getTeamsOfUser(manager);
		List<Team> managedTeams = new ArrayList<Team>();
		for (Team team : teams) {
			if (team.getManagers() != null && team.getManagers().contains(manager)) 
				managedTeams.add(team);
		}
		
		return managedTeams;
	}
}
