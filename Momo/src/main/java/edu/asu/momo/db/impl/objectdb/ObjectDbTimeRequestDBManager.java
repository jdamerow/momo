package edu.asu.momo.db.impl.objectdb;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.ITimeRequestDBManager;

@Service
public class ObjectDbTimeRequestDBManager
		implements ITimeRequestDBManager {
	
	@Autowired
	protected IDatabaseManager dbmanager;


	@Override
	@Transactional
	public boolean storeTimeRequest(TimeRequest request) {
		dbmanager.store(request);
		return true;
	}
	
	@Override
	@Transactional
	public boolean updateTimeRequest(TimeRequest request) {
		dbmanager.update(request);
		return true;
	}

	@Override
	@Transactional
	public List<TimeRequest> getAllTimeRequests(String username, int status) {
		TypedQuery<TimeRequest> query =
			      dbmanager.getManager().createQuery("SELECT t FROM TimeRequest t WHERE t.username = :username and t.status = :status", TimeRequest.class);
		query.setParameter("username", username);
		query.setParameter("status", status);
		List<TimeRequest> results = query.getResultList();
		return results;
	}

	@Override
	@Transactional
	public TimeRequest getTimeRequest(String id) {
		return (TimeRequest) dbmanager.get(id, TimeRequest.class);
	}

	@Override
	public List<TimeRequest> getAllTimeRequests(String username) {
		TypedQuery<TimeRequest> query =
			      dbmanager.getManager().createQuery("SELECT t FROM TimeRequest t WHERE t.username = :username", TimeRequest.class);
		query.setParameter("username", username);
		List<TimeRequest> results = query.getResultList();
		return results;
	}

}
