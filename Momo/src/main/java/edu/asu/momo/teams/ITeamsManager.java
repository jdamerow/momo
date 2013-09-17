package edu.asu.momo.teams;

import java.util.List;

import edu.asu.momo.core.Team;

public interface ITeamsManager {

	public abstract boolean addTeam(Team team);

	public abstract List<Team> getTeamsOfUser(String user);

	public abstract List<Team> getAllTeams();

	public abstract Team getTeam(String id);

	public abstract boolean updateTeam(Team team);

	public abstract boolean deleteTeam(String id);

}