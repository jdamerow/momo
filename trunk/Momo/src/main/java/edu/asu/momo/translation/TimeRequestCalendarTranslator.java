package edu.asu.momo.translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.request.backing.TimeChangeRequestCalendarBean;

@Service
public class TimeRequestCalendarTranslator {
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;

	public TimeChangeRequestCalendarBean translateToCalendarBean(TimeChangeRequest request) {
		TimeChangeRequestCalendarBean bean = new TimeChangeRequestCalendarBean();
		bean.setMakeupEndDate(request.getMakeupEndDate());
		bean.setMakeupStartDate(request.getMakeupStartDate());
		bean.setNewEndDate(request.getNewEndDate());
		bean.setNewStartDate(request.getNewStartDate());
		bean.setOldEndDate(request.getOldEndDate());
		bean.setOldStartDate(request.getOldStartDate());
		User user = userManager.getUserById(request.getUsername());
		if (user != null) {
			bean.setUser(userTranslator.translateUser(user));
		}
		return bean;
	}
}
