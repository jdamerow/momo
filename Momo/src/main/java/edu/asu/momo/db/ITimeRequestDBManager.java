package edu.asu.momo.db;

import java.util.List;

import edu.asu.momo.core.TimeRequest;

public interface ITimeRequestDBManager {

	public abstract boolean storeTimeRequest(TimeRequest request);

	public abstract List<TimeRequest> getAllTimeRequests(String username, int status);

	public abstract TimeRequest getTimeRequest(final String id);

	public abstract List<TimeRequest> getAllTimeRequests(final String username);

	public abstract boolean updateTimeRequest(TimeRequest request);

}