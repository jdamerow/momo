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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.projects.ProjectTranslator;
import edu.asu.momo.recording.BreakTimeManager;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.web.projects.backing.ProjectBackingBean;
import edu.asu.momo.web.recording.backing.RecordingBackingBean;
import edu.asu.momo.web.recording.backing.SignOutBackingBean;

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
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principle) {
		
		List<TimeEntry> entries = timeEntryManager.getOpenTimeEntries(principle.getName());
		
		if (entries == null || entries.size() == 0) {
			List<Project> projects = projectManager.getProjectsOfUser(principle.getName());
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
		
		model.addAttribute("currentEntries", entries);
		return "auth/welcomeStopMultiple";
		
	}
	
}
