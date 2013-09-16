package edu.asu.momo.web.recording;

import java.security.Principal;
import java.text.DateFormat;
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

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.recording.TimeEntryTranslator;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;
import edu.asu.momo.web.recording.backing.TimePeriod;

@Controller
public class TimesheetController {

	@Autowired
	private ITimeEntryManager timeEntryManager;
	
	@Autowired
	private TimeEntryTranslator translator;
	
	@RequestMapping(value = "auth/timesheets/overview")
	public String showTimeEntries(Principal principal, ModelMap map) {
		
		List<TimeEntry> entries = getLastWeeksTimeEntries(principal);
		
		List<TimeEntryBacking> backingEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			backingEntries.add(translator.translate(entry));
		}
		map.addAttribute(new TimePeriod());
		map.addAttribute("entries", backingEntries);
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
			} catch (ParseException e) {
				results.addError(new ObjectError("", e.getMessage()));
				entries = getLastWeeksTimeEntries(principal);
			}
			
		}
		
		
		
		List<TimeEntryBacking> backingEntries = new ArrayList<TimeEntryBacking>();
		for (TimeEntry entry : entries) {
			backingEntries.add(translator.translate(entry));
		}
		
		map.addAttribute("entries", backingEntries);
		return "auth/timesheets/overview";
		
	}
	
	protected List<TimeEntry> getLastWeeksTimeEntries(Principal principal) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date weekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		
		List<TimeEntry> entries = timeEntryManager.getTimeEntries(principal.getName(), weekAgo, new Date());
		Collections.sort(entries, new Comparator<TimeEntry>() {

			@Override
			public int compare(TimeEntry arg0, TimeEntry arg1) {
				return arg0.getStartDate().compareTo(arg1.getStartDate());
			}
		});
		return entries;
	}
}
