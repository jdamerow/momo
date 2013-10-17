package edu.asu.momo.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.projects.ProjectTranslator;
import edu.asu.momo.recording.BreakTimeManager;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.recording.TimeEntryTranslator;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.recording.backing.RecordingBackingBean;
import edu.asu.momo.web.recording.backing.SignOutBackingBean;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ITimeEntryManager timeEntryManager;
	
	@Autowired
	private IProjectManager projectManager;
	
	@Autowired
	private ProjectTranslator projectTranslator;
	
	@Autowired
	private BreakTimeManager breakTimeManager;
	
	@Autowired
	private ITeamsManager teamManager;
	
	@Autowired
	private TimeEntryTranslator entryTranslator;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principal) {
		
		List<TimeEntry> entries = timeEntryManager.getOpenTimeEntries(principal.getName());
		List<Team> managedTeams = teamManager.getManagedTeams(principal.getName());
		model.addAttribute("managedTeams", managedTeams);
		
		if (entries == null || entries.size() == 0) {
			List<Project> projects = projectManager.getProjectsOfUser(principal.getName());
			List<ProjectBackingBean> beans = new ArrayList<ProjectBackingBean>();
			
			for (Project project : projects) {
				ProjectBackingBean bean = projectTranslator.translate(project);
				beans.add(bean);
			}
			
			model.addAttribute("projects", beans);
			model.addAttribute("recording", new RecordingBackingBean());
			
			return "auth/welcome";
		}
		
		if (entries.size() == 1) {
			model.addAttribute("entry", entries.get(0));
			model.addAttribute(new SignOutBackingBean());
			
			model.addAttribute("breakTimes", breakTimeManager.getBreakTimes());
			return "auth/welcomeStop";
		}
		
		List<TimeEntryBacking> translatedEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			translatedEntries.add(entryTranslator.translate(entry));
		}
		
		model.addAttribute("currentEntries", translatedEntries);
		return "auth/welcomeStopMultiple";
		
	}
	
	@RequestMapping(value = "auth/welcome/{id}", method = RequestMethod.GET)
	public String welcomeById(@PathVariable("id") String id, Model model, Principal principal) {
		
		TimeEntry entries = timeEntryManager.getTimeEntry(id);
		
		List<Team> managedTeams = teamManager.getManagedTeams(principal.getName());
		model.addAttribute("managedTeams", managedTeams);
		
		if (entries == null) {
			List<Project> projects = projectManager.getProjectsOfUser(principal.getName());
			List<ProjectBackingBean> beans = new ArrayList<ProjectBackingBean>();
			
			for (Project project : projects) {
				ProjectBackingBean bean = projectTranslator.translate(project);
				beans.add(bean);
			}
			
			model.addAttribute("projects", beans);
			model.addAttribute("recording", new RecordingBackingBean());
			
			return "auth/welcome";
		}
		
		model.addAttribute("entry", entries);
		SignOutBackingBean bean = new SignOutBackingBean();
		bean.setId(id);
		model.addAttribute(bean);
			
		model.addAttribute("breakTimes", breakTimeManager.getBreakTimes());
		return "auth/welcomeStop";
		
	}
	
}
