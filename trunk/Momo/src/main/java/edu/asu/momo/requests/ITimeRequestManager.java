package edu.asu.momo.requests;

import java.util.List;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeRequest;

public interface ITimeRequestManager {

	public abstract boolean storeTimeChangeRequest(TimeChangeRequest request);

	public abstract List<TimeRequest> getRequestsOfUser(String user, int status);

	public abstract TimeRequest getRequest(String id);

	public abstract boolean updateTimeRequest(TimeRequest request);

}