package edu.asu.momo.db;

import java.util.List;

import edu.asu.momo.core.Team;

/**
 * Interface to be implemented by team manager classes.
 * 
 * @author Julia Damerow
 *
 */
public interface ITeamDBManager {

	public abstract boolean updateTeam(Team team);

	public abstract List<Team> getAllTeams();

	public abstract List<Team> getTeamsOfUser(String user);

	public abstract Team getTeam(String id);

	public abstract boolean deleteTeam(String id);

}