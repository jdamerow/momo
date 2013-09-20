package edu.asu.momo.web.teams;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class TeamMainController {
	
	@Autowired
	private ITeamsManager teamsManager;
	
	@Autowired
	private TeamTranslator teamTranslator;

	@RequestMapping(value = "auth/team/manage")
	public String showTeamOverview(Principal principal, ModelMap map) {
		List<Team> teams = teamsManager.getAllTeams();
		Collections.sort(teams, new Comparator<Team>() {

			@Override
			public int compare(Team o1, Team o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		List<TeamBackingBean> teamBeans = new ArrayList<TeamBackingBean>();
		for (Team team : teams)
			teamBeans.add(teamTranslator.translateTeam(team));
		
		map.addAttribute("teams", teamBeans);
		return "auth/team/manage";
	}
}
