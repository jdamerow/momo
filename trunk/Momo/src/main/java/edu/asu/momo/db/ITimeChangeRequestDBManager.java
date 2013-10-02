package edu.asu.momo.db;

import java.util.Date;
import java.util.List;

import edu.asu.momo.core.TimeChangeRequest;


public interface ITimeChangeRequestDBManager extends ITimeRequestDBManager  {

	public abstract List<TimeChangeRequest> getTimeChangeRequests(final String username, final int status,
			final Date startDate, final Date endDate);

}