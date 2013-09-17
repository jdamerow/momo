package edu.asu.momo.web.recording;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.projects.IProjectManager;
import edu.asu.momo.recording.ITimeEntryManager;
import edu.asu.momo.web.recording.backing.RecordingBackingBean;

@Controller
public class SignInController {
	
	@Autowired
	private ITimeEntryManager entryManager;
	
	@Autowired
	private IProjectManager projectManager;

	@RequestMapping(value = "auth/signIn")
	public String signIn(@ModelAttribute("recording") RecordingBackingBean recording, Principal principle) {
		String projectId = recording.getProjectId();
		
		entryManager.startRecording(principle.getName(), projectId, null);
		return "redirect:/auth/welcome";
	}
	
	@RequestMapping(value = "auth/signOut")
	public String signOut(Principal principle) {
		List<TimeEntry> entries = entryManager.getOpenTimeEntries(principle.getName());
		TimeEntry entry = entries.get(0);
		entryManager.stopRecording(entry);
		
		return "redirect:/auth/welcome";
	}

}
