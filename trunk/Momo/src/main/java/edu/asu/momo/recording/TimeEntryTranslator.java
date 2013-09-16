package edu.asu.momo.recording;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.web.recording.backing.TimeEntryBacking;

@Service
public class TimeEntryTranslator {

	public TimeEntryBacking translate(TimeEntry entry) {
		TimeEntryBacking backingEntry = new TimeEntryBacking();
		
		Date startDate = entry.getStartDate();
//		DateFormat format = DateFormat.getDateInstance();
		SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
		
		String date = format.format(startDate);
		
		backingEntry.setDate(date);
		
		DateFormat timeFormat = DateFormat.getTimeInstance();
		backingEntry.setStartDate(timeFormat.format(startDate));
		
		if (entry.getEndDate() != null) {
			backingEntry.setEndDate(timeFormat.format(entry.getEndDate()));
			
			long startTime = startDate.getTime();
			long endTime = entry.getEndDate().getTime();
			
			// millisecs
			float diff = endTime - startTime;
			float hours = diff / (60 * 60 * 1000);
			
			DecimalFormat floatForm = new DecimalFormat("#.##");
			backingEntry.setTime(floatForm.format(hours));
		}
		else
			backingEntry.setTime("-");
		
		return backingEntry;
	}

}
