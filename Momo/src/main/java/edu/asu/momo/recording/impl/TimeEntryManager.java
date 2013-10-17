package edu.asu.momo.recording.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.ITimeEntryDBManager;
import edu.asu.momo.recording.ITimeEntryManager;

@Service
public class TimeEntryManager implements ITimeEntryManager {

	@Autowired
	private ITimeEntryDBManager dbManager;
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.recording.impl.ITimeEntryManager#startRecording(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TimeEntry startRecording(String user, String projectId, String notes) {
		TimeEntry entry = new TimeEntry();
		entry.setUserId(user);
		entry.setStartDate(new Date());
		entry.setNotes(notes);
		entry.setProjectId(projectId);
		entry.setId(UUID.randomUUID().toString());
		
		dbManager.updateTimeEntry(entry);
		return entry;
	}
	
	@Override
	public TimeEntry stopRecording(TimeEntry entry) {
		entry.setEndDate(new Date());
		dbManager.updateTimeEntry(entry);
		return entry;
	}
	
	@Override
	public List<TimeEntry> getOpenTimeEntries(final String user) {
		return dbManager.getOpenTimeEntries(user);
	}
	
	@Override
	public List<TimeEntry> getTimeEntries(final String user, Date startDate, Date endDate) {
		
		return dbManager.getTimeEntries(user, startDate, endDate);
	}

	@Override
	public TimeEntry getTimeEntry(String id) {
		return dbManager.getTimeEntry(id);
	}

}
