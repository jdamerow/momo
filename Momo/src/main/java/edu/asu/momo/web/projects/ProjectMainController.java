package edu.asu.momo.web.projects;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.Team;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.projects.ProjectTranslator;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.teams.backing.TeamBackingBean;

@Controller
public class ProjectMainController {
	
	@Autowired
	private IProjectManager projectManager;
	
	@Autowired 
	private ITeamsManager teamsManager;
	
	@Autowired
	private ProjectTranslator projectTranslator;
	
	@Autowired
	private TeamTranslator teamTranslator;

	@RequestMapping(value = "auth/projects/overview")
	public String showProjects(Principal principal, ModelMap map) {
		
		List<Team> teamsOfUser = teamsManager.getTeamsOfUser(principal.getName());
		
		
		Map<TeamBackingBean, List<ProjectBackingBean>> finalMap = new HashMap<TeamBackingBean, List<ProjectBackingBean>>();
		
		for (Team team : teamsOfUser) {
			List<Project> projects = projectManager.getProjectsOfTeam(team.getId());
			
			List<ProjectBackingBean> beans = new ArrayList<ProjectBackingBean>();
			
			for (Project project : projects) {
				ProjectBackingBean bean = projectTranslator.translate(project);
				beans.add(bean);
			}
			
			TeamBackingBean tBB = teamTranslator.translateTeam(team);
			if (team.getManagers().contains(principal.getName()))
				tBB.setIsTeamManager(true);
			
			finalMap.put(tBB, beans);
		}
		
		map.addAttribute("projectMap", finalMap);
		
		return "auth/projects/overview";
	}
}
