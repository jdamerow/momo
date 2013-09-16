package edu.asu.momo.projects;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.Team;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

@Service
public class ProjectTranslator {

	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;
	
	@Autowired
	private TeamTranslator teamTranslator;
	
	@Autowired
	private ITeamsManager teamManager;
	
	public ProjectBackingBean translate(Project project) {
		ProjectBackingBean bean = new ProjectBackingBean();
		bean.setDescription(project.getDescription());
		bean.setId(project.getId());
		bean.setName(project.getName());
		bean.setMembers(new ArrayList<UserBackingBean>());
		
		for (String member : project.getMembers()) {
			User user = userManager.getUserById(member);
			if (user != null)
				bean.getMembers().add(userTranslator.translateUser(user));
		}
		
		String teamId = project.getTeamId();
		Team team = teamManager.getTeam(teamId);
		if (team != null) {
			bean.setTeam(teamTranslator.translateTeam(team));
		}
		
		return bean;
	}
}
