package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.ITimeEntryDBManager;

@Service
public class TimeEntryDBManager extends DBManager implements ITimeEntryDBManager {

	
	/* (non-Javadoc)
	 * @see edu.asu.momo.recording.impl.ITimeEntryManager#getOpenTimeEntries(edu.asu.momo.user.User)
	 */
	@Override
	public List<TimeEntry> getOpenTimeEntries(final String user) {
		ObjectSet<TimeEntry> results = database.query(new Predicate<TimeEntry>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7055638663957054281L;

			@Override
			public boolean match(TimeEntry arg0) {
				if (arg0.getEndDate() == null && arg0.getUserId().equals(user))
					return true;
				return false;
			}
		});
		
		List<TimeEntry> entries = new ArrayList<TimeEntry>();
		entries.addAll(results);
		
		return entries;
	}
	
	@Override
	public List<TimeEntry> getTimeEntries(final String user, Date startDate, final Date endDate) {
		final Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		
		ObjectSet<TimeEntry> results = database.query(new Predicate<TimeEntry>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TimeEntry arg0) {
				if (arg0.getUserId().equals(user)) {
					Calendar currentCal = Calendar.getInstance();
					currentCal.setTime(arg0.getStartDate());
					if (currentCal.after(startCal) && 
							(arg0.getEndDate() == null || endDate.after(arg0.getEndDate())))
						return true;
				}
				return false;
			}
		});
		
		List<TimeEntry> entries = new ArrayList<TimeEntry>();
		entries.addAll(results);
		return entries;
	}

	/* (non-Javadoc)
	 * @see edu.asu.momo.recording.impl.ITimeEntryManager#addTimeEntry(edu.asu.momo.core.TimeEntry)
	 */
	@Override
	public boolean updateTimeEntry(TimeEntry entry) {
		return updateObject(entry);
	}
	
	
	
}
