package edu.asu.momo.requests.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.db.ITimeChangeRequestDBManager;
import edu.asu.momo.requests.ITimeRequestManager;

@Service
public class TimeRequestsManager implements ITimeRequestManager {

	@Autowired
	private ITimeChangeRequestDBManager dbManager;
	
	@Override
	public boolean storeTimeChangeRequest(TimeChangeRequest request) {
		request.setId(UUID.randomUUID().toString());
		return dbManager.storeTimeRequest(request);
	}
	
	@Override
	public boolean updateTimeRequest(TimeRequest request) {
		return dbManager.storeTimeRequest(request);
	}
	
	@Override
	public List<TimeRequest> getRequestsOfUser(String user, int status) {
		return dbManager.getAllTimeRequests(user, status);
	}
	
	@Override
	public TimeRequest getRequest(String id) {
		return dbManager.getTimeRequest(id);
	}
}
