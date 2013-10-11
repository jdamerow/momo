package edu.asu.momo.web.request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.requests.TimeRequestTranslator;
import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

@Controller
public class CalendarController {
	
	@Autowired
	private ITimeRequestManager requestManager;
	
	@Autowired 
	private TimeRequestTranslator requestTranslator;

	@RequestMapping(value = "auth/requests/showCalendar")
	public String showCalendar() {
		return "auth/requests/showCalendar";
	}

	@RequestMapping(value = "auth/requests/calendarevents.json")
	public String getEvents(ModelMap map, HttpServletRequest request) {
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		Date startDate = null;
		Date endDate = null;
		Calendar cal = Calendar.getInstance();

		if (start != null && !start.trim().isEmpty()) {
			cal.setTimeInMillis(new Long(start));
			startDate = cal.getTime();
		}

		if (end != null && !end.trim().isEmpty()) {
			cal.setTimeInMillis(new Long(end));
			endDate = cal.getTime();
		}
		
		if (startDate == null || endDate == null)
			return "auth/error";
		
		List<TimeChangeRequest> requests = requestManager.getRequests(startDate, endDate, IStatus.APPROVED);

		List<TimeChangeRequestBean> requestBeans = new ArrayList<TimeChangeRequestBean>();
		for (TimeChangeRequest timeRequest : requests) {
			TimeChangeRequestBean bean = requestTranslator.translateTimeChangeRequest(timeRequest);
			if (bean != null)
				requestBeans.add(bean);
		}
		
		map.addAttribute("requests", requests);
		
		return "auth/requests/events";
	}
}
