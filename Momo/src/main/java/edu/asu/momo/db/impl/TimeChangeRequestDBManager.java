package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.db.ITimeChangeRequestDBManager;

@Service
public class TimeChangeRequestDBManager extends TimeRequestDBManager implements
		ITimeChangeRequestDBManager {

	@Override
	public List<TimeChangeRequest> getTimeChangeRequests(final String username,
			final int status, final Date startDate, final Date endDate) {

		ObjectSet<TimeChangeRequest> results = database
				.query(new Predicate<TimeChangeRequest>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean match(TimeChangeRequest arg0) {
						if (username != null && !arg0.getUsername().equals(username))
							return false;

						if (!(arg0.getStatus() == status))
							return false;

						/*
						 * if old shift lies in time frame return true
						 */
						if (arg0.getOldStartDate().getTime() >= startDate
								.getTime()
								&& arg0.getOldEndDate().getTime() <= endDate
										.getTime())
							return true;

						/*
						 * if makeup day lies in tiem frame
						 */
						if (arg0.getMakeupStartDate().getTime() >= startDate
								.getTime()
								|| arg0.getMakeupEndDate().getTime() <= endDate.getTime())
							return true;

						return false;
					}
				});
		
		List<TimeChangeRequest> requests = new ArrayList<TimeChangeRequest>();
		requests.addAll(results);
		return requests;
	}

}
