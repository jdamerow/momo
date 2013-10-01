package edu.asu.momo.requests;

import edu.asu.momo.core.TimeChangeRequest;

public interface IRequestNotificationManager {

	public abstract void sendTimeChangeRequestNotificationToManager(
			String username, TimeChangeRequest request);

	public abstract void sendRejectionEmail(String username, String managerId,
			TimeChangeRequest request);

	public abstract void sendApprovalEmail(String username, String managerId,
			TimeChangeRequest request);

}