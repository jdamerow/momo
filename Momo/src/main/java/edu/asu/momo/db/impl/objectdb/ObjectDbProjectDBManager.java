package edu.asu.momo.db.impl.objectdb;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.Project;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.IProjectDBManager;

@Service
public class ObjectDbProjectDBManager implements IProjectDBManager {
	
	@Autowired
	private IDatabaseManager dbmanager;

	@Override
	@Transactional
	public Project getProject(String id) {
		return (Project) dbmanager.get(id, Project.class);
	}

	@Override
	@Transactional
	public List<Project> getProjectsOfUser(String user) {
		TypedQuery<Project> query = dbmanager.getManager().createQuery(
			        "SELECT project FROM Project project WHERE :name in :members", Project.class);
		query.setParameter("name", user);
		List<Project> projects = query.getResultList();
		return projects;
	}

	@Override
	@Transactional
	public boolean addProject(Project project) {
		dbmanager.store(project);
		return true;
	}

	@Override
	@Transactional
	public List<Project> getProjectsOfTeam(String teamId) {
		TypedQuery<Project> query = dbmanager.getManager().createQuery(
			        "SELECT project FROM Project project WHERE project.teamId = :team", Project.class);
		query.setParameter("team", teamId);
		List<Project> projects = query.getResultList();
		return projects;
	}

}
