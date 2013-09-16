package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import edu.asu.momo.core.Project;
import edu.asu.momo.db.IDatabaseManager;

@Service
public class ProjectDBManager implements IProjectDBManager {

	@Autowired
	private IDatabaseManager dbManager;
	private ObjectContainer database;
	
	@PostConstruct
	public void init() {
		database = dbManager.getClient();
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.IProjectDBManager#getProject(java.lang.String)
	 */
	@Override
	public Project getProject(String id) {
		Project example = new Project();
		example.setId(id);
		ObjectSet<Project> results = database.queryByExample(example);
		
		if (results.size() > 0)
			return results.get(0);
		return null;
	}
	
	@Override
	public List<Project> getProjectsOfTeam(final String teamId) {
		Project example = new Project();
		example.setTeamId(teamId);
		ObjectSet<Project> results = database.queryByExample(example);
		
		List<Project> projects = new ArrayList<Project>();
		for (Project p : results) {
			projects.add(p);
		}
		return projects;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.IProjectDBManager#getProjectsOfUser(java.lang.String)
	 */
	@Override
	public List<Project> getProjectsOfUser(final String user) {
		ObjectSet<Project> results = database.query(new Predicate<Project>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4328333383768986694L;

			@Override
			public boolean match(Project arg0) {
				if (arg0.getMembers().contains(user))
					return true;
				return false;
			}
		});
		
		List<Project> projects = new ArrayList<Project>();
		projects.addAll(results);
		return projects;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.IProjectDBManager#addProject(edu.asu.momo.core.Project)
	 */
	@Override
	public boolean addProject(Project project) {
		database.store(project);
		database.commit();
		return true;
	}
	
	@PreDestroy
	public void shutdown() {
		database.close();
	}
}
