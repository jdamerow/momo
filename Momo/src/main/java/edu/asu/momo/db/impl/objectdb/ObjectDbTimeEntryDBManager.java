package edu.asu.momo.db.impl.objectdb;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.TimeEntry;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.ITimeEntryDBManager;

@Service
public class ObjectDbTimeEntryDBManager implements ITimeEntryDBManager {
	
	@Autowired
	private IDatabaseManager dbmanager;

	@Override
	@Transactional
	public List<TimeEntry> getOpenTimeEntries(String user) {
		TypedQuery<TimeEntry> query =
			      dbmanager.getManager().createQuery("SELECT t FROM TimeEntry t WHERE t.endDate IS NULL", TimeEntry.class);
		List<TimeEntry> results = query.getResultList();
		return results;
	}

	@Override
	@Transactional
	public boolean updateTimeEntry(TimeEntry entry) {
		dbmanager.update(entry);
		return true;
	}

	@Override
	@Transactional
	public List<TimeEntry> getTimeEntries(String user, Date startDate,
			Date endDate) {
		TypedQuery<TimeEntry> query =
			      dbmanager.getManager().createQuery("SELECT t FROM TimeEntry t WHERE t.userId == :username AND t.startDate >= :startdate AND (t.endDate IS NULL OR t.endDate < :enddate)", TimeEntry.class);
		query.setParameter("username", user);
		query.setParameter("startdate", startDate);
		query.setParameter("enddate", endDate);
		List<TimeEntry> results = query.getResultList();
		return results;
	}

	@Override
	@Transactional
	public TimeEntry getTimeEntry(String id) {
		return (TimeEntry) dbmanager.get(id, TimeEntry.class);
	}

}
