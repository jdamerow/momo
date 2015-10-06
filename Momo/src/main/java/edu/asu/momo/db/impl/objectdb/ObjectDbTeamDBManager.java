package edu.asu.momo.db.impl.objectdb;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.ITeamDBManager;

@Service
public class ObjectDbTeamDBManager implements ITeamDBManager {

	@Autowired
	private IDatabaseManager dbmanager;

	@Override
	@Transactional
	public boolean updateTeam(Team team) {
		dbmanager.update(team);
		return true;
	}

	@Override
	@Transactional
	public List<Team> getAllTeams() {
		TypedQuery<Team> query =
			      dbmanager.getManager().createQuery("SELECT team FROM Team team", Team.class);
		List<Team> results = query.getResultList();
		return results;
	}

	@Override
	@Transactional
	public List<Team> getTeamsOfUser(String user) {
		TypedQuery<Team> query = dbmanager.getManager().createQuery(
			        "SELECT team FROM Team team WHERE :name IN team.members OR :name IN team.managers", Team.class);
		query.setParameter("name", user);
		List<Team> projects = query.getResultList();
		return projects;
	}

	@Override
	@Transactional
	public Team getTeam(String id) {
		return (Team) dbmanager.get(id, Team.class);
	}

	@Override
	@Transactional
	public boolean deleteTeam(String id) {
		return dbmanager.delete(id, Team.class);
	}

}
