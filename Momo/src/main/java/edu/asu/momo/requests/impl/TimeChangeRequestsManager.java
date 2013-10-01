package edu.asu.momo.requests.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.db.ITimeChangeRequestDBManager;

@Service
public class TimeChangeRequestsManager {

	@Autowired
	private ITimeChangeRequestDBManager dbManager;
	
	public boolean storeTimeChangeRequest(TimeChangeRequest request) {
		request.setId(UUID.randomUUID().toString());
		return dbManager.storeTimeRequest(request);
	}
}
