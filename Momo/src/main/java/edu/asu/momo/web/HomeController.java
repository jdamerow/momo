package edu.asu.momo.web;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.ITimeEntryDBManager;
import edu.asu.momo.recording.ITimeEntryManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ITimeEntryManager timeEntryManager;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principle) {
		
		List<TimeEntry> entries = timeEntryManager.getOpenTimeEntries(principle.getName());
		
		if (entries == null || entries.size() == 0) {
			return "auth/welcome";
		}
		
		if (entries.size() == 1) {
			model.addAttribute("entry", entries.get(0));
			return "auth/welcomeStop";
		}
		
		model.addAttribute("currentEntries", entries);
		return "auth/welcomeStopMultiple";
		
	}
	
}
