package edu.asu.momo.recording.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeEntry;

@Service
public class TimeEntryUtility implements ITimeEntryUtility {

	/* (non-Javadoc)
	 * @see edu.asu.momo.recording.impl.ITimeEntryUtility#calculateTotal(java.util.List)
	 */
	@Override
	public float calculateTotal(List<TimeEntry> entries) {
		float total = 0;
		
		if (entries == null)
			return total;
		
		for (TimeEntry entry : entries) {
			float worked = getHoursWorked(entry);
			
			if (worked > 0) {
				float breakTime = entry.getBreakTime();
				total += worked - breakTime;
			}
		}
		
		return total;
	}
	
	@Override
	public String getTimeAsHoursAndMinutes(float time) {
		int hoursInt = (int) time;
		float minutesPart = time - hoursInt;
		int minutesInt = (int) (60 * minutesPart);
		return hoursInt + "h " + minutesInt + "m";
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.recording.impl.ITimeEntryUtility#getHoursWorked(edu.asu.momo.core.TimeEntry)
	 */
	@Override
	public float getHoursWorked(TimeEntry entry) {
		if (entry.getEndDate() == null) 
			return -1;
		
		Date startDate = entry.getStartDate();
		
		long startTime = startDate.getTime();
		long endTime = entry.getEndDate().getTime();
		
		// millisecs
		float diff = endTime - startTime;
		float hours = diff / (60 * 60 * 1000);
		
		return hours;
	}
}
