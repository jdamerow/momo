package edu.asu.momo.web.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

@Controller
public class TimeChangeRequestController {

	@RequestMapping(value = "auth/requests/timeChange")
	public String prepareChangeRequestPage(ModelMap model) {
		model.addAttribute(new TimeChangeRequestBean());
		return "auth/requests/timeChange";
	}
}
