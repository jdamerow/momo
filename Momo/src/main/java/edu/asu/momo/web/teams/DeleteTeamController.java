package edu.asu.momo.web.teams;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class DeleteTeamController {

	@Autowired
	private ITeamsManager teamsManager;

	@Autowired
	private TeamTranslator teamTranslator;

	@RequestMapping(value = "auth/team/delete/{teamId}")
	public String prepareDelete(Principal principal,
			@PathVariable("teamId") String teamId, ModelMap map) {
		Team team = teamsManager.getTeam(teamId);

		if (team == null) {
			return "redirect:/auth/team/manage";
		}

		map.addAttribute("team", teamTranslator.translateTeam(team));
		return "auth/team/delete";
	}
	
	@RequestMapping(value = "auth/team/delete")
	public String prepareDeleteWithNullId(Principal principal, ModelMap map) {
		Team team = teamsManager.getTeam(null);

		if (team == null) {
			return "redirect:/auth/team/manage";
		}

		map.addAttribute("team", teamTranslator.translateTeam(team));
		return "auth/team/delete";
	}

	@RequestMapping(value = "auth/team/executeDelete")
	public String executeDelete(Principal principal,
			@ModelAttribute TeamBackingBean teamBean, ModelMap map) {
		String id = teamBean.getId();
		if (teamBean.getId().isEmpty())
			id = null;
		Team team = teamsManager.getTeam(id);

		if (team == null) {
			return "redirect:/auth/team/manage";
		}

		teamsManager.deleteTeam(id);
		return "redirect:/auth/team/manage";
	}

}
