package edu.asu.momo.web.teams;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

@Controller
public class EditTeamController {
	
	@Autowired
	private ITeamsManager teamsManager;
	
	@Autowired
	private TeamTranslator teamTranslator;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private UserTranslator userTranslater;

	@RequestMapping(value = "auth/team/edit/{teamId}")
	public String prepareEditTeam(Principal principal, ModelMap map, @PathVariable("teamId") String teamId) {
		
		Team team = teamsManager.getTeam(teamId);
		if (team == null) {
			return "redirect:/auth/team/manage";
		}
		
		TeamBackingBean tbb = teamTranslator.translateTeam(team);
		Map<String, UserBackingBean> managersMap = new HashMap<String, UserBackingBean>();
		Map<String, UserBackingBean> membersMap = new HashMap<String, UserBackingBean>();
		
		if (tbb.getManagers() != null) {
			for (UserBackingBean ubb : tbb.getManagers()) {
				managersMap.put(ubb.getUsername(), ubb);
			}
		}
		if (tbb.getMembers() != null) {
			for (UserBackingBean ubb : tbb.getMembers()) {
				membersMap.put(ubb.getUsername(), ubb);
			}
		}
		
		List<User> users = userManager.getAllUsers();
		List<UserBackingBean> managers = new ArrayList<UserBackingBean>();
		List<UserBackingBean> members = new ArrayList<UserBackingBean>();
		
		for (User user : users) {
			UserBackingBean bb = userTranslater.translateUser(user);
			if (managersMap.containsKey(user.getUsername()))
				managers.add(managersMap.get(user.getUsername()));
			else 
				managers.add(bb);
			
			if (membersMap.containsKey(user.getUsername()))
				members.add(membersMap.get(user.getUsername()));
			else
				members.add(bb);
		}
		
		map.addAttribute("managers", managers);
		map.addAttribute("members", members);
		map.addAttribute("teamBackingBean", tbb);
		return "auth/team/editTeam";
	}
	
	@RequestMapping(value = "auth/team/updateTeam")
	public String updateTeam(@Valid @ModelAttribute TeamBackingBean teamBean, BindingResult results, Principal principal, ModelMap map) {
		if (results.hasErrors()) {
			map.addAttribute("users", userManager.getAllUsers());
			
			return "auth/team/showAddTeam";
		}
		
		Team team = teamsManager.getTeam(teamBean.getId());
		teamTranslator.updateTeam(team, teamBean);
		teamsManager.updateTeam(team);
		
		return "redirect:/auth/team/manage";
	}
}
