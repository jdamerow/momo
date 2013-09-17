package edu.asu.momo.db;

import java.util.List;

import edu.asu.momo.core.Team;

public interface ITeamDBManager {

	public abstract boolean updateTeam(Team team);

	public abstract List<Team> getAllTeams();

	public abstract List<Team> getTeamsOfUser(String user);

	public abstract Team getTeam(String id);

	public abstract boolean deleteTeam(String id);

}