package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.ITeamDBManager;

@Service
public class TeamDBManager implements ITeamDBManager {

	@Autowired
	private IDatabaseManager dbManager;
	private ObjectContainer database;
	
	@PostConstruct
	public synchronized void init() {
		database = dbManager.getClient();
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITeamDBManager#updateTeam(edu.asu.momo.core.Team)
	 */
	@Override
	public boolean updateTeam(Team team) {
		database.store(team);
		database.commit();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITeamDBManager#getAllTeams()
	 */
	@Override
	public List<Team> getAllTeams() {
		ObjectSet<Team> results = database.query(Team.class);
		List<Team> teams = new ArrayList<Team>();
		
		teams.addAll(results);
		return teams;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITeamDBManager#getTeamsOfUser(java.lang.String)
	 */
	@Override
	public List<Team> getTeamsOfUser(final String user) {
		ObjectSet<Team> results = database.query(new Predicate<Team>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(Team arg0) {
				if (arg0.getMembers().contains(user) || arg0.getManagers().contains(user))
					return true;
				return false;
			}
		});
		
		List<Team> teams = new ArrayList<Team>();
		
		teams.addAll(results);
		return teams;
	}
	
	@Override
	public Team getTeam(String id) {
		Team example = new Team();
		example.setId(id);
		
		ObjectSet<Team> results = database.queryByExample(example);
		if (results.size() > 0)
			return results.get(0);
		return null;
	}
	
	@Override
	public boolean deleteTeam(String id) {
		Team team = getTeam(id);
		database.delete(team);
		return true;
	}
	

}
