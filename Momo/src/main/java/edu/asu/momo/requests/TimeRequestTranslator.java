package edu.asu.momo.requests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

@Service
public class TimeRequestTranslator {

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
		
		return request;
	}
	
}
