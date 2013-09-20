package edu.asu.momo.web.projects;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.teams.backing.TeamBackingBean;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class AddProjectController {
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IProjectManager projectManager;
	@Autowired
	private ITeamsManager teamsManager;
	@Autowired
	private TeamTranslator teamTranslator;

	@RequestMapping(value = "auth/projects/addProject")
	public String showAddProject(Principal principal, ModelMap map) {
		map.addAttribute("project", new ProjectBackingBean());
		
		List<Team> teams = teamsManager.getTeamsOfUser(principal.getName());
		
		List<TeamBackingBean> teamsBeans = new ArrayList<TeamBackingBean>();
		for (Team team : teams) {
			if (team.getManagers().contains(principal.getName()))
				teamsBeans.add(teamTranslator.translateTeam(team));
		}
		
		map.addAttribute("teams", teamsBeans);
		
		return "auth/projects/addProject";
	}
	
	@RequestMapping(value = "auth/projects/executeAdd") 
	public String executeAdd(@Valid @ModelAttribute("project") ProjectBackingBean projectBean, BindingResult results, Principal principal, ModelMap map) {
		/*
		 * If there are validation errors
		 */
		if (results.hasErrors()) {
			List<Team> teams = teamsManager.getTeamsOfUser(principal.getName());
			
			List<TeamBackingBean> teamsBeans = new ArrayList<TeamBackingBean>();
			for (Team team : teams) {
				if (team.getManagers().contains(principal.getName()))
					teamsBeans.add(teamTranslator.translateTeam(team));
			}
			
			map.addAttribute("teams", teamsBeans);
			
			return "auth/projects/addProject";
		}
		
		/*
		 * Otherwise save
		 */
		projectManager.saveProject(projectBean.getName(), projectBean.getDescription(), projectBean.getTeam().getId());
		return "redirect:/auth/projects/overview";
	}
}
