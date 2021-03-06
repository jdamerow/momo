package edu.asu.momo.web.timesheets.team;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.recording.ITimeEntryUtility;
import edu.asu.momo.recording.TimeEntryTranslator;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.translation.TimeRequestTranslator;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;
import edu.asu.momo.web.request.backing.TimeRequestBean;
import edu.asu.momo.web.timesheets.backing.TimeSheetSelection;
import edu.asu.momo.web.user.backing.UserBackingBean;

@Controller
public class TeamTimesheetsOverviewController {

	@Autowired
	private ITeamsManager teamsManager;

	@Autowired
	private TeamTranslator teamTranslator;

	@Autowired
	private ITimeEntryManager timeEntryManager;
	
	@Autowired
	private TimeEntryTranslator timeTranslator;

	@Autowired
	private IUserManager userManager;

	@Autowired
	private UserTranslator userTranslator;
	
	@Autowired
	private ITimeEntryUtility utility;
	
	@Autowired
	private ITimeRequestManager requestManager;
	
	@Autowired
	private TimeRequestTranslator requestTranslator;
	

	@RequestMapping(value = "auth/timesheets/team/{teamId}")
	public String showTeamTimesheets(Principal principal, ModelMap map,
			@PathVariable("teamId") String teamId) {

		Team team = teamsManager.getTeam(teamId);
		if (team == null) {
			return "redirect:/auth/timesheets/overview";
		}

		if (!team.getManagers().contains(principal.getName())) {
			return "auth/timesheets/team/forbidden";
		}

		List<UserBackingBean> managers = new ArrayList<UserBackingBean>();
		getManagers(managers, team);	

		List<UserBackingBean> members = new ArrayList<UserBackingBean>();
		getMembers(members, team);

		map.addAttribute("team", teamTranslator.translateTeam(team));
		map.addAttribute("managers", managers);
		map.addAttribute("members", members);
		map.addAttribute("timeSheetSelection", new TimeSheetSelection());

		return "auth/timesheets/team";
	}

	@RequestMapping(value = "auth/timesheets/team/refreshTimesheet")
	public String refreshTimesheet(Principal principal,
			@ModelAttribute TimeSheetSelection selection, ModelMap map) {
		
		if (selection == null)
			return "redirect:/auth/timesheets/overview";
		
		if (selection.getStartDay() == null) {
			if (selection.getTeamId() != null)
				return "redirect:/auth/timesheets/team/" + selection.getTeamId();
			else
				return "redirect:/auth/timesheets/overview";
		}

		List<TimeEntry> entries = new ArrayList<TimeEntry>();
		List<TimeChangeRequest> approvedRequest = new ArrayList<TimeChangeRequest>();
		List<TimeChangeRequest> deniedRequest = new ArrayList<TimeChangeRequest>();


		if (!(selection.getStartDay() == null || selection.getStartDay().trim()
				.isEmpty())
				|| selection.getEndDay() == null
				|| selection.getEndDay().trim().isEmpty()) {
			
			
			try {
				Date start;
				Date end;
				
				SimpleDateFormat parserSDF = new SimpleDateFormat("MM/dd/yyyy");
				start = parserSDF.parse(selection.getStartDay());
				end = parserSDF.parse(selection.getEndDay());

				if (selection.getManagers() != null) {
					for (UserBackingBean man : selection.getManagers()) {
						entries.addAll(timeEntryManager.getTimeEntries(man.getUsername(),
								start, end));
						
						/*
						 * get requests
						 */
						approvedRequest.addAll(requestManager.getTimeChangeRequests(man.getUsername(), IStatus.APPROVED, start, end));
						deniedRequest.addAll(requestManager.getTimeChangeRequests(man.getUsername(), IStatus.REJECTED, start, end));
					}
				}
				
				if (selection.getMembers() != null) {
					for (UserBackingBean mem : selection.getMembers()) {
						entries.addAll(timeEntryManager.getTimeEntries(mem.getUsername(),
								start, end));
						
						/*
						 * get requests
						 */
						approvedRequest.addAll(requestManager.getTimeChangeRequests(mem.getUsername(), IStatus.APPROVED, start, end));
						deniedRequest.addAll(requestManager.getTimeChangeRequests(mem.getUsername(), IStatus.REJECTED, start, end));
					}
				}
				
				Collections.sort(entries, new Comparator<TimeEntry>() {

					@Override
					public int compare(TimeEntry arg0, TimeEntry arg1) {
						return arg0.getStartDate().compareTo(
								arg1.getStartDate());
					}
				});
			} catch (ParseException e) {
				if (selection.getManagers() != null) {
					for (UserBackingBean man : selection.getManagers()) {
						entries.addAll(getLastWeeksTimeEntries(man.getUsername()));
						/*
						 * Find time change requests
						 */
						approvedRequest.addAll(getLastWeeksTimeRequests(man.getUsername(), IStatus.APPROVED));
						deniedRequest.addAll(getLastWeeksTimeRequests(man.getUsername(), IStatus.REJECTED));
					}
				}
				
				if (selection.getMembers() != null) {
					for (UserBackingBean mem : selection.getMembers()) {
						entries.addAll(getLastWeeksTimeEntries(mem.getUsername()));
						approvedRequest.addAll(getLastWeeksTimeRequests(mem.getUsername(), IStatus.APPROVED));
						deniedRequest.addAll(getLastWeeksTimeRequests(mem.getUsername(), IStatus.REJECTED));
					}
				}
			}
		}
		
		List<TimeEntryBacking> backingEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			backingEntries.add(timeTranslator.translate(entry));
		}
		
		/*
		 * Add requests
		 */
		List<TimeRequestBean> approvedbeans = new ArrayList<TimeRequestBean>();
		for (TimeChangeRequest req : approvedRequest) {
			approvedbeans.add(requestTranslator.translateTimeChangeRequest(req));
		}
		map.addAttribute("approvedRequests", approvedbeans);
		
		List<TimeRequestBean> deniedbeans = new ArrayList<TimeRequestBean>();
		for (TimeChangeRequest req : deniedRequest) {
			deniedbeans.add(requestTranslator.translateTimeChangeRequest(req));
		}
		map.addAttribute("deniedRequests", deniedbeans);
		
		/*
		 * Get user
		 */
		Team team = teamsManager.getTeam(selection.getTeamId());
		
		List<UserBackingBean> managers = new ArrayList<UserBackingBean>();
		getManagers(managers, team);

		List<UserBackingBean> members = new ArrayList<UserBackingBean>();
		getMembers(members, team);
		
		/*
		 * Calculate total of all work times
		 */
		float totalWorked = utility.calculateTotal(entries);
		map.addAttribute("total", utility.getTimeAsHoursAndMinutes(totalWorked));

		map.addAttribute("team", teamTranslator.translateTeam(team));
		map.addAttribute("managers", managers);
		map.addAttribute("members", members);	
		map.addAttribute("entries", backingEntries);
		
		return "auth/timesheets/team";
	}
	
	protected void getManagers(List<UserBackingBean> managers, Team team) {
		if (team.getManagers() != null) {
			for (String manId : team.getManagers()) {
				User man = userManager.getUserById(manId);
				if (man != null) {
					managers.add(userTranslator.translateUser(man));
				}
			}
		}
	}
	
	protected void getMembers(List<UserBackingBean> members, Team team) {
		if (team.getMembers() != null) {
			for (String memId : team.getMembers()) {
				User mem = userManager.getUserById(memId);
				if (mem != null) {
					members.add(userTranslator.translateUser(mem));
				}
			}
		}
	}

	protected List<TimeEntry> getLastWeeksTimeEntries(String username) {
		Date weekAgo = getAWeekAgo();

		List<TimeEntry> entries = timeEntryManager.getTimeEntries(
				username, weekAgo, new Date());
		Collections.sort(entries, new Comparator<TimeEntry>() {

			@Override
			public int compare(TimeEntry arg0, TimeEntry arg1) {
				return arg0.getStartDate().compareTo(arg1.getStartDate());
			}
		});
		return entries;
	}
	
	protected Date getAWeekAgo() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date weekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return weekAgo;
	}
	
	protected List<TimeChangeRequest> getLastWeeksTimeRequests(String user, int status) {
		Date weekAgo = getAWeekAgo();
		
		return requestManager.getTimeChangeRequests(user, status, weekAgo, new Date());
	}
}
