package edu.asu.momo.web.request;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.requests.IRequestNotificationManager;
import edu.asu.momo.requests.IStatus;
import edu.asu.momo.requests.ITimeRequestManager;
import edu.asu.momo.translation.TimeRequestTranslator;
import edu.asu.momo.web.HomeController;
import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

@Controller
public class TimeChangeRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TimeRequestTranslator translator;
	
	@Autowired
	private ITimeRequestManager requestManager;

	@Autowired
	private IRequestNotificationManager notifcationManager;
	
	@RequestMapping(value = "auth/requests/timeChange")
	public String prepareChangeRequestPage(ModelMap model) {
		model.addAttribute(new TimeChangeRequestBean());
		return "auth/requests/timeChange";
	}
	
	@RequestMapping(value = "auth/requests/requestTimeChange", method = RequestMethod.POST)
	public String requestTimeChange(@Valid @ModelAttribute("timeChangeRequestBean") TimeChangeRequestBean requestBean,
			BindingResult results, ModelMap model, Principal principal) {
		if (results.hasErrors()) {
			return "auth/requests/timeChange";
		}
		
		TimeChangeRequest request;
		try {
			request = translator.translateTimeChangeRequestBean(requestBean);
		} catch (ParseException e) {
			logger.error("Couldn't translate time change request.", e);
			return "auth/error";
		}
		
		request.setUsername(principal.getName());
		request.setStatus(IStatus.PENDING);
		request.setRequestedOn(new Date());
		
		requestManager.storeTimeChangeRequest(request);
		notifcationManager.sendTimeChangeRequestNotificationToManager(principal.getName(), request);
		
		return "redirect:/auth/welcome";
	}
}
