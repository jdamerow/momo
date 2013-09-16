package edu.asu.momo.web.recording;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.recording.ITimeEntryManager;

@Controller
public class SignInController {
	
	@Autowired
	private ITimeEntryManager entryManager;

	@RequestMapping(value = "auth/signIn")
	public String signIn(Principal principle) {
		TimeEntry entry = entryManager.startRecording(principle.getName(), null, null);
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
