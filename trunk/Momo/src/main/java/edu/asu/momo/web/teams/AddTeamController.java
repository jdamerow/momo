package edu.asu.momo.web.teams;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;

@Controller
public class AddTeamController {
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private ITeamsManager teamsManager;
	
	@Autowired
	private TeamTranslator translator;

	@RequestMapping(value = "auth/team/showAddTeam")
	public String showAddTeam(Principal principal, ModelMap map) {
		map.addAttribute("users", userManager.getAllUsers());
		map.addAttribute(new TeamBackingBean());
		
		return "auth/team/showAddTeam";
	}
	
	@RequestMapping(value = "auth/team/addTeam")
	public String addTeam(@Valid @ModelAttribute TeamBackingBean teamBean, BindingResult results, Principal principal, ModelMap map) {
		if (results.hasErrors()) {
			map.addAttribute("users", userManager.getAllUsers());
			
			return "auth/team/showAddTeam";
		}
		
		Team team = translator.translate(teamBean);
		teamsManager.addTeam(team);
		
		return "redirect:/auth/team/manage";
	}
}
