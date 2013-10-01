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
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.ITimeRequestManager#getAllTimeRequests(java.lang.String)
	 */
	@Override
	public List<TimeRequest> getAllTimeRequests(final String username) {
		ObjectSet<TimeRequest> results = database.query(new Predicate<TimeRequest>() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TimeRequest arg0) {
				if (((TimeRequest)arg0).getUsername().equals(username))
					return true;
				return false;
			}
		});
		
		List<TimeRequest> requests = new ArrayList<TimeRequest>();
		requests.addAll(results);
		return requests;
	}
}
