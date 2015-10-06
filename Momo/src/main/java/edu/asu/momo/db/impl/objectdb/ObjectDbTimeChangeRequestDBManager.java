package edu.asu.momo.db.impl.objectdb;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.db.ITimeChangeRequestDBManager;

@Service
public class ObjectDbTimeChangeRequestDBManager extends ObjectDbTimeRequestDBManager implements ITimeChangeRequestDBManager {

	@Override
	@Transactional
	public List<TimeChangeRequest> getTimeChangeRequests(String username,
			int status, Date startDate, Date endDate) {
		TypedQuery<TimeChangeRequest> query =
			      dbmanager.getManager().createQuery("SELECT t FROM TimeChangeRequest t WHERE t.username = :username AND ((t.oldStartDate >= :startDate AND t.oldEndDate <= :endDate) OR (t.makeupStartDate >= :startDate AND t.makeupEndDate <= :endDate))", TimeChangeRequest.class);
		query.setParameter("username", username);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<TimeChangeRequest> results = query.getResultList();
		return results;
	}

}
