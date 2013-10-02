package edu.asu.momo.web.timesheets;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.recording.ITimeEntryUtility;
import edu.asu.momo.recording.TimeEntryTranslator;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.requests.TimeRequestTranslator;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;
import edu.asu.momo.web.recording.backing.TimePeriod;
import edu.asu.momo.web.request.backing.TimeRequestBean;

@Controller
public class TimesheetController {

	@Autowired
	private ITimeEntryManager timeEntryManager;
	
	@Autowired
	private TimeEntryTranslator translator;
	
	@Autowired
	private ITeamsManager teamsManager;
	
	@Autowired
	private ITimeEntryUtility utility;
	
	@Autowired
	private ITimeRequestManager requestManager;
	
	@Autowired
	private TimeRequestTranslator requestTranslator;
	
	@RequestMapping(value = "auth/timesheets/overview")
	public String showTimeEntries(Principal principal, ModelMap map) {
		
		List<TimeEntry> entries = getLastWeeksTimeEntries(principal);
		
		List<TimeEntryBacking> backingEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			backingEntries.add(translator.translate(entry));
		}
		map.addAttribute(new TimePeriod());
		map.addAttribute("entries", backingEntries);
		
		/*
		 * Calculate total of all work times
		 */
		float totalWorked = utility.calculateTotal(entries);
		map.addAttribute("total", utility.getTimeAsHoursAndMinutes(totalWorked));

		
		/*
		 * Find teams user is manager of
		 */
		List<Team> teams = teamsManager.getManagedTeams(principal.getName());
		map.addAttribute("teams", teams);
		
		/*
		 * Find time change requests
		 */
		List<TimeChangeRequest> approvedRequest = getLastWeeksTimeRequests(principal, IStatus.APPROVED);
		List<TimeChangeRequest> deniedRequest = getLastWeeksTimeRequests(principal, IStatus.REJECTED);
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
		
		return "auth/timesheets/overview";
	}
	

	@RequestMapping(value = "auth/timesheets/refreshTimesheet")
	public String showTimeEntries(@Valid @ModelAttribute TimePeriod timePeriod, BindingResult results, Principal principal, ModelMap map) {
		List<TimeEntry> entries;
		if (results.hasErrors()) {
			entries = getLastWeeksTimeEntries(principal);
		}
		else {
			Date start;
			Date end;
			List<TimeChangeRequest> approvedRequest;
			List<TimeChangeRequest> deniedRequest;
			try {
				SimpleDateFormat parserSDF=new SimpleDateFormat("MM/dd/yyyy");
				start = parserSDF.parse(timePeriod.getStartDay());
				end = parserSDF.parse(timePeriod.getEndDay());

				entries = timeEntryManager.getTimeEntries(principal.getName(), start, end);
				Collections.sort(entries, new Comparator<TimeEntry>() {

					@Override
					public int compare(TimeEntry arg0, TimeEntry arg1) {
						return arg0.getStartDate().compareTo(arg1.getStartDate());
					}
				});
				
				approvedRequest = requestManager.getTimeChangeRequests(principal.getName(), IStatus.APPROVED, start, end);
				deniedRequest = requestManager.getTimeChangeRequests(principal.getName(), IStatus.REJECTED, start, end);
			} catch (ParseException e) {
				results.addError(new ObjectError("", e.getMessage()));
				entries = getLastWeeksTimeEntries(principal);
				/*
				 * Find time change requests
				 */
				approvedRequest = getLastWeeksTimeRequests(principal, IStatus.APPROVED);
				deniedRequest = getLastWeeksTimeRequests(principal, IStatus.REJECTED);
			}
			
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
		}
		
		List<TimeEntryBacking> backingEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			backingEntries.add(translator.translate(entry));
		}
		map.addAttribute("entries", backingEntries);
		
		/*
		 * Calculate total of all work times
		 */
		float totalWorked = utility.calculateTotal(entries);
		map.addAttribute("total", utility.getTimeAsHoursAndMinutes(totalWorked));

		
		List<Team> teams = teamsManager.getManagedTeams(principal.getName());
		map.addAttribute("teams", teams);
		
		return "auth/timesheets/overview";
		
	}
	
	protected List<TimeEntry> getLastWeeksTimeEntries(Principal principal) {
		Date weekAgo = getAWeekAgo();
		
		List<TimeEntry> entries = timeEntryManager.getTimeEntries(principal.getName(), weekAgo, new Date());
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
	
	protected List<TimeChangeRequest> getLastWeeksTimeRequests(Principal principal, int status) {
		Date weekAgo = getAWeekAgo();
		
		return requestManager.getTimeChangeRequests(principal.getName(), status, weekAgo, new Date());
	}
}
