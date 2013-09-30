package edu.asu.momo.db;

import java.util.List;

import edu.asu.momo.core.TimeRequest;

public interface ITimeRequestManager {

	public abstract boolean storeTimeRequest(TimeRequest request);

	public abstract List<TimeRequest> getAllTimeRequests(String username);

}