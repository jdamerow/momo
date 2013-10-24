package edu.asu.momo.web.recording;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.web.recording.backing.RecordingBackingBean;
import edu.asu.momo.web.recording.backing.SignOutBackingBean;

/**
 * This class controls clocking in and out of Momo.
 * @author Julia Damerow
 *
 */
@Controller
public class SignInController {
	
	@Autowired
	private ITimeEntryManager entryManager;
	
	@Autowired
	private IProjectManager projectManager;

	@RequestMapping(value = "auth/signIn")
	public String signIn(@ModelAttribute("recording") RecordingBackingBean recording, Principal principle, ModelMap map) {
		
		/*
		 * If user is already clocked in just go back to welcome page.
		 */
		List<TimeEntry> entries = entryManager.getOpenTimeEntries(principle.getName());
		if (entries.size() > 0)
			return "redirect:/auth/welcome";
		
		/*
		 * Else start recording.
		 */
		String projectId = recording.getProjectId();
		
		entryManager.startRecording(principle.getName(), projectId, recording.getNotes());
		
		return "redirect:/auth/welcome";
	}
	
	@RequestMapping(value = "auth/signOut")
	public String signOut(@ModelAttribute SignOutBackingBean signOut, Principal principle) {
		
		/*
		 * if there were several, stop the one with given id
		 */
		TimeEntry entry = null;
		if (signOut.getId() != null && !signOut.getId().trim().isEmpty()) {
			entry = entryManager.getTimeEntry(signOut.getId());
		}
		
		if (entry == null) {
			List<TimeEntry> entries = entryManager.getOpenTimeEntries(principle.getName());
			if (entries == null || entries.isEmpty()) {
				return "redirect:/auth/welcome";
			}
			entry = entries.get(0);
		}
		
		
		entryManager.stopRecording(entry, signOut.getNotes(), signOut.getBreakTime());
		
		return "redirect:/auth/welcome";
	}
	
}
