package edu.asu.momo.projects.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Project;
import edu.asu.momo.db.impl.IProjectDBManager;
import edu.asu.momo.projects.IProjectManager;

/**
 * Service class to manage {@link Project}s. Acts as bridge between
 * controller and database layer.
 * @author Julia Damerow
 *
 */
@Service
public class ProjectManager implements IProjectManager {

	@Autowired
	private IProjectDBManager dbManager;
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.projects.impl.IProjectManager#getProjectsOfUser(java.lang.String)
	 */
	@Override
	public List<Project> getProjectsOfUser(String user) {
		return dbManager.getProjectsOfUser(user);
	}
	
	@Override
	public List<Project> getProjectsOfTeam(String teamId) {
		return dbManager.getProjectsOfTeam(teamId);
	}
	
	@Override
	public boolean saveProject(String name, String description, String teamId) {
		Project project = new Project();
		project.setDescription(description);
		project.setName(name);
		project.setTeamId(teamId);
		project.setId(UUID.randomUUID().toString());
		return dbManager.addProject(project);
	}
}
