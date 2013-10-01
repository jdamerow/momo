package edu.asu.momo.recording;

import java.util.List;

import edu.asu.momo.core.TimeEntry;

public interface ITimeEntryUtility {

	public abstract float calculateTotal(List<TimeEntry> entries);

	public abstract float getHoursWorked(TimeEntry entry);

	public abstract String getTimeAsHoursAndMinutes(float time);

}