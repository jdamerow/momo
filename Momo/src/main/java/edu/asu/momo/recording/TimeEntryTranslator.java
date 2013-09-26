package edu.asu.momo.recording;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Project;
import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.projects.ProjectTranslator;
import edu.asu.momo.recording.impl.ITimeEntryUtility;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;

@Service
public class TimeEntryTranslator {
	
	@Autowired
	private ProjectTranslator projectTranslator;
	
	@Autowired
	private IProjectManager projectManager;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;
	
	@Autowired
	private ITimeEntryUtility utility;
	
	@Autowired
	private BreakTimeManager breakManager;

	public TimeEntryBacking translate(TimeEntry entry) {
		TimeEntryBacking backingEntry = new TimeEntryBacking();
		
		/*
		 * Set project
		 */
		if (entry.getProjectId() != null) {
			Project p = projectManager.getProject(entry.getProjectId());
			if (p != null)
				backingEntry.setProject(projectTranslator.translate(p));
		}
		
		/*
		 * Set user
		 */
		User user = userManager.getUserById(entry.getUserId());
		if (user != null) {
			backingEntry.setUser(userTranslator.translateUser(user));
		}
		
		/*
		 * set notes
		 */
		backingEntry.setNotes(entry.getNotes());
		
		/*
		 * Set start time as long
		 */
		backingEntry.setDateAsMSec(entry.getStartDate().getTime());
		
		/*
		 * Set BreakTime
		 */
		BreakTime breakTime = breakManager.getBreakTime(entry.getBreakTime());
		if (breakTime != null)
			backingEntry.setBreakTime(breakTime.getLabel());
		else
			backingEntry.setBreakTime("-");
		
		/*
		 * Set time and date
		 */
		Date startDate = entry.getStartDate();
		SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");
		
		String date = format.format(startDate);
		
		backingEntry.setDate(date);
		
		DateFormat timeFormat = DateFormat.getTimeInstance();
		backingEntry.setStartDate(timeFormat.format(startDate));
		
		if (entry.getEndDate() != null) {
			backingEntry.setEndDate(timeFormat.format(entry.getEndDate()));
			
			float hours = utility.getHoursWorked(entry);
			
			DecimalFormat floatForm = new DecimalFormat("#.##");
			backingEntry.setTime(floatForm.format(hours));
			
			backingEntry.setTimeInHM(utility.getTimeAsHoursAndMinutes(hours));
		}
		else
			backingEntry.setTime("-");
		
		return backingEntry;
	}

}
