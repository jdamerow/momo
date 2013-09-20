package edu.asu.momo.db;

import java.util.List;

import edu.asu.momo.core.Project;

/**
 * Interface to be implemented by database connector class.
 * 
 * @author Julia Damerow
 *
 */
public interface IProjectDBManager {

	public abstract Project getProject(String id);

	public abstract List<Project> getProjectsOfUser(String user);

	public abstract boolean addProject(Project project);

	public abstract List<Project> getProjectsOfTeam(final String teamId);

}