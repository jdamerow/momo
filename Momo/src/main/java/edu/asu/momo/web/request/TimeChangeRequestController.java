package edu.asu.momo.web.request;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

@Controller
public class TimeChangeRequestController {

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
		
		return "";
	}
}
