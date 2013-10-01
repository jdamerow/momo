package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.db.ITimeRequestDBManager;

public class TimeRequestDBManager extends DBManager implements ITimeRequestDBManager {

	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITimeRequestManager#storeTimeRequest(edu.asu.momo.core.TimeRequest)
	 */
	@Override
	public boolean storeTimeRequest(TimeRequest request) {
		return updateObject(request);
	}
	
	@Override
	public TimeRequest getTimeRequest(final String id) {
		ObjectSet<TimeRequest> results = database.query(new Predicate<TimeRequest>() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TimeRequest arg0) {
				if (((TimeRequest)arg0).getId().equals(id))
					return true;
				return false;
			}
		});
		
		if (results != null && results.size() > 0)
			return results.get(0);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITimeRequestManager#getAllTimeRequests(java.lang.String)
	 */
	@Override
	public List<TimeRequest> getAllTimeRequests(final String username, final int status) {
		ObjectSet<TimeRequest> results = database.query(new Predicate<TimeRequest>() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TimeRequest arg0) {
				if (((TimeRequest)arg0).getUsername().equals(username) && arg0.getStatus() == status)
					return true;
				return false;
			}
		});
		
		List<TimeRequest> requests = new ArrayList<TimeRequest>();
		requests.addAll(results);
		return requests;
	}
}
