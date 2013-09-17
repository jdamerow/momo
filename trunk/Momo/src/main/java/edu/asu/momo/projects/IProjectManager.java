package edu.asu.momo.projects;

import java.util.List;

import edu.asu.momo.core.Project;

public interface IProjectManager {

	public abstract List<Project> getProjectsOfUser(String user);

	public abstract List<Project> getProjectsOfTeam(String teamId);

	public abstract boolean saveProject(String name, String description, String teamId);

	public abstract Project getProject(String id);

}