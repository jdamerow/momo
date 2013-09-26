package edu.asu.momo.projects.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.Team;
import edu.asu.momo.db.IProjectDBManager;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.teams.ITeamsManager;

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
	
	@Autowired
	private ITeamsManager teamsManager;
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.projects.impl.IProjectManager#getProjectsOfUser(java.lang.String)
	 */
	@Override
	public List<Project> getProjectsOfUser(String user) {
		List<Team> teams = teamsManager.getTeamsOfUser(user);
		List<Project> projects = new ArrayList<Project>();
		for (Team team : teams)
		{
			List<Project> projectsOfTeam = dbManager.getProjectsOfTeam(team.getId());
			if (projectsOfTeam != null)
				projects.addAll(projectsOfTeam);
		}
		return projects;
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
	
	@Override
	public Project getProject(String id) {
		return dbManager.getProject(id);
	}
}
