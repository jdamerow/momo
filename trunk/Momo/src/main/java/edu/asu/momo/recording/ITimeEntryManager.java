package edu.asu.momo.recording;

import java.util.Date;
import java.util.List;

import edu.asu.momo.core.TimeEntry;

public interface ITimeEntryManager {

	public abstract TimeEntry startRecording(String user, String projectId,
			String notes);

	public abstract TimeEntry stopRecording(TimeEntry entry);

	public abstract List<TimeEntry> getOpenTimeEntries(final String user);

	public abstract List<TimeEntry> getTimeEntries(final String user, Date startDate, Date endDate);

}