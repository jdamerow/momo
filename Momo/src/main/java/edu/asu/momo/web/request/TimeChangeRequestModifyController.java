package edu.asu.momo.web.request;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.requests.IRequestNotificationManager;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.translation.TimeRequestTranslator;
import edu.asu.momo.web.Constants;
import edu.asu.momo.web.HomeController;
import edu.asu.momo.web.request.backing.ActionRequestBean;

@Controller
public class TimeChangeRequestModifyController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TimeRequestTranslator translator;
	
	@Autowired
	private ITimeRequestManager requestManager;

	@Autowired
	private IRequestNotificationManager notifcationManager;
	
	@Autowired
	private ITeamsManager teamsManager;
	
	
	@RequestMapping(value = Constants.VIEW_REQUEST + "{id}")
	public String showRequest(@PathVariable("id") String id, Principal principal, ModelMap map) {
		
		TimeRequest timeRequest = requestManager.getRequest(id);
		if (timeRequest == null)
			return "auth/error";
		
		boolean isRequester = false;
		if (timeRequest.getUsername().equals(principal.getName()))
			isRequester = true;
		
		boolean isTeamManager = false;
		List<Team> teams = teamsManager.getTeamsOfUser(timeRequest.getUsername());
		for (Team team : teams) {
			if (team.getManagers().contains(principal.getName())) {
				isTeamManager = true;
				break;
			}
		}
		
		boolean isApprovable = false;
		if (timeRequest.getStatus() == IStatus.PENDING)
			isApprovable = true;
		
		/*
		 * FIXME: needs to be change when there are more types of requests
		 */
		map.addAttribute("timeRequest", translator.translateTimeChangeRequest((TimeChangeRequest)timeRequest));
		map.addAttribute("isRequester", isRequester);
		map.addAttribute("isTeamManager", isTeamManager);
		map.addAttribute(new ActionRequestBean());
		map.addAttribute("isApprovable", isApprovable);
		
		return "auth/requests/view";
	}
	
	@RequestMapping(value = "auth/requests/view")
	public String showRequest() {		
		return "redirect:/auth/requests/list";
	}
	
	@RequestMapping(value = "auth/requests/decideRequest", params = "submit_approval")
	public String approveRequest(@ModelAttribute ActionRequestBean actionRequestBean, Principal principal) {
		TimeRequest timeRequest = requestManager.getRequest(actionRequestBean.getRequestId());
		if (timeRequest == null) {
			logger.error("Time request is null: " + actionRequestBean.getRequestId());
			return "auth/error";
		}
		
		boolean isTeamManager = isTeamManager(principal.getName(), timeRequest);
		
		if (!isTeamManager) {
			logger.error("No team manager rights. Access denied for " + principal.getName());
			return "auth/error";
		}
		
		timeRequest.setStatus(IStatus.APPROVED);
		timeRequest.setReviewNotes(actionRequestBean.getNotes());
		timeRequest.setReviewedBy(principal.getName());
		timeRequest.setRejectedApprovedOn(new Date());
		requestManager.updateTimeRequest(timeRequest);
		notifcationManager.sendApprovalEmail(timeRequest.getUsername(), principal.getName(), (TimeChangeRequest) timeRequest);
		
		return "redirect:/auth/requests/list";
	}
	
	protected boolean isTeamManager(String username, TimeRequest timeRequest) {
		boolean isTeamManager = false;
		List<Team> teams = teamsManager.getTeamsOfUser(timeRequest.getUsername());
		for (Team team : teams) {
			if (team.getManagers().contains(username)) {
				isTeamManager = true;
				break;
			}
		}
		return isTeamManager;
	}
	
	@RequestMapping(value = "auth/requests/decideRequest", params = "submit_rejection")
	public String rejectRequest(@ModelAttribute ActionRequestBean actionRequestBean, Principal principal) {
		TimeRequest timeRequest = requestManager.getRequest(actionRequestBean.getRequestId());
		if (timeRequest == null) {
			logger.error("Time request is null: " + actionRequestBean.getRequestId());
			return "auth/error";
		}
		
		boolean isTeamManager = isTeamManager(principal.getName(), timeRequest);
		
		if (!isTeamManager) {
			logger.error("No team manager rights. Access denied for " + principal.getName());
			return "auth/error";
		}
		
		timeRequest.setStatus(IStatus.REJECTED);
		timeRequest.setReviewNotes(actionRequestBean.getNotes());
		timeRequest.setReviewedBy(principal.getName());
		timeRequest.setRejectedApprovedOn(new Date());
		requestManager.updateTimeRequest(timeRequest);
		notifcationManager.sendRejectionEmail(timeRequest.getUsername(), principal.getName(), (TimeChangeRequest) timeRequest);
		
		return "redirect:/auth/requests/list";
	}
}
