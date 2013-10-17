package edu.asu.momo.db;

import java.util.Date;
import java.util.List;

import edu.asu.momo.core.TimeEntry;

/**
 * Interface to be implemented by time entry manager classes.
 * 
 * @author Julia Damerow
 *
 */
public interface ITimeEntryDBManager {

	public abstract List<TimeEntry> getOpenTimeEntries(String user);

	public abstract boolean updateTimeEntry(TimeEntry entry);

	public abstract List<TimeEntry> getTimeEntries(final String user, Date startDate, Date endDate);

	public TimeEntry getTimeEntry(String id);
}