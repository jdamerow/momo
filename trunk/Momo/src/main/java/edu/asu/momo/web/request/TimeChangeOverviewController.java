package edu.asu.momo.web.request;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.requests.TimeRequestTranslator;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.web.request.backing.TimeRequestBean;

@Controller
public class TimeChangeOverviewController {
	
	@Autowired
	private ITeamsManager teamManager;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private ITimeRequestManager requestManager;
	
	@Autowired
	private TimeRequestTranslator requestTranslator;

	@RequestMapping(value = "auth/requests/list")
	public String listRequests(Principal principal, ModelMap map) {
		
		List<Team> teams = teamManager.getTeamsOfUser(principal.getName());
		List<Team> managedTeams = new ArrayList<Team>();
		for (Team team : teams) {
			if (team.getManagers() != null && team.getManagers().contains(principal.getName())) 
				managedTeams.add(team);
		}
		
		List<TimeRequest> requests = new ArrayList<TimeRequest>();
		for (Team team : managedTeams) {
			for (String user : team.getMembers()) {
				requests.addAll(requestManager.getRequestsOfUser(user, IStatus.PENDING));
			}
		}
		
		List<TimeRequestBean> beans = new ArrayList<TimeRequestBean>();
		for (TimeRequest req : requests) {
			if (req instanceof TimeChangeRequest) {
				TimeRequestBean bean = requestTranslator.translateTimeChangeRequest((TimeChangeRequest)req);
				if (bean != null)
					beans.add(bean);
			}
		}
		
		map.addAttribute("requests", beans);
		return "auth/requests/list";
	}
	
}
