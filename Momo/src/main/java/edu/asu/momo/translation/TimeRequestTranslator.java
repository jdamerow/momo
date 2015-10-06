package edu.asu.momo.translation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.request.backing.TimeChangeRequestBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

@Service
@PropertySource("classpath:status.properties")
public class TimeRequestTranslator {
	
	@Autowired
	private IUserDBManager userManager;
	
	@Autowired 
	private UserTranslator userTranslator;
	
	@Autowired
	private Environment env;

	public TimeChangeRequest translateTimeChangeRequestBean(TimeChangeRequestBean requestBean) throws ParseException {
		TimeChangeRequest request = new TimeChangeRequest();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		Date startShift = df.parse(requestBean.getShiftDay() + " " + requestBean.getShiftStart());
		Date endShift = df.parse(requestBean.getShiftDay() + " " + requestBean.getShiftEnd());
		
		request.setOldStartDate(startShift);
		request.setOldEndDate(endShift);
		
		/*
		 * The old shift was changed
		 */
		if (requestBean.getNewShiftStart() != null && !requestBean.getNewShiftStart().trim().isEmpty()) {
			Date newStartShift = df.parse(requestBean.getShiftDay() + " " + requestBean.getNewShiftStart());
			Date newEndShift = df.parse(requestBean.getShiftDay() + " " + requestBean.getNewShiftEnd());
			
			request.setNewStartDate(newStartShift);
			request.setNewEndDate(newEndShift);
		}
		
		/*
		 * There is a makeup day given
		 */
		if (requestBean.getMakeupDay() != null && !requestBean.getMakeupDay().isEmpty()) {
			Date makeupStart = df.parse(requestBean.getMakeupDay() + " " + requestBean.getMakeupShiftStart());
			Date makeupEnd = df.parse(requestBean.getMakeupDay() + " " + requestBean.getMakeupShiftEnd());
			
			request.setMakeupStartDate(makeupStart);
			request.setMakeupEndDate(makeupEnd);
		}
		
		/*
		 * Set notes
		 */
		request.setRequestNotes(requestBean.getRequestNotes());
		
		return request;
	}
	
	public TimeChangeRequestBean translateTimeChangeRequest(TimeChangeRequest request) {
		TimeChangeRequestBean bean = new TimeChangeRequestBean();
		
		/*
		 * set user
		 */
		User user = userManager.getUserById(request.getUsername());
		if (user == null)
			return null;
		
		UserBackingBean userBean = userTranslator.translateUser(user);
		bean.setRequester(userBean);
		
		/*
		 * set shift
		 */
		Date shiftStart = request.getOldStartDate();
		Date shiftEnd = request.getOldEndDate();
		SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");
		
		/*
		 * day
		 */
		bean.setShiftDay(format.format(shiftStart));
		bean.setShiftDayMS(shiftStart.getTime());
		
		/*
		 * time
		 */
		DateFormat timeFormat = DateFormat.getTimeInstance();
		bean.setShiftStart(timeFormat.format(shiftStart));
		bean.setShiftEnd(timeFormat.format(shiftEnd));
		
		if (request.getNewStartDate() != null) {
			bean.setNewShiftStart(timeFormat.format(request.getNewStartDate()));
			bean.setNewShiftEnd(timeFormat.format(request.getNewEndDate()));
		}
		
		if (request.getMakeupStartDate() != null) {
			bean.setMakeupDay(format.format(request.getMakeupStartDate()));
			bean.setMakeupDayMS(request.getMakeupStartDate().getTime());
			
			bean.setMakeupShiftStart(timeFormat.format(request.getMakeupStartDate()));
			bean.setMakeupShiftEnd(timeFormat.format(request.getMakeupEndDate()));
		}
		
		bean.setRequestNotes(request.getRequestNotes());
		bean.setId(request.getId());
		bean.setStatus(env.getProperty(request.getStatus() + ""));
		
		/*
		 * Set requested on date
		 */
		SimpleDateFormat formatWithTime = new SimpleDateFormat("EE, MMM dd, yyyy (hh:mm aa)");
		
		if (request.getRequestedOn() != null) {
			bean.setRequestedOn(formatWithTime.format(request.getRequestedOn()));
			bean.setRequestedOnMS(request.getRequestedOn().getTime());
		}
		
		if (request.getReviewedBy() != null && !request.getReviewedBy().trim().isEmpty()) {
			User reviewer = userManager.getUserById(request.getReviewedBy());
			if (reviewer != null) {
				bean.setReviewer(userTranslator.translateUser(reviewer));
			}
			if (request.getRequestedOn() != null) {
				bean.setReviewedOn(formatWithTime.format(request.getRequestedOn()));
			}
			bean.setReviewNotes(request.getReviewNotes());
		}
		
		return bean;
	}
}
