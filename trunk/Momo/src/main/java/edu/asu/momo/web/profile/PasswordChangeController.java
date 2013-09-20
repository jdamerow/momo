package edu.asu.momo.web.profile;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.IUserFactory;
import edu.asu.momo.user.User;
import edu.asu.momo.web.profile.backing.PasswordBackingBean;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class PasswordChangeController {
	
	@Autowired
	private IUserFactory userFactory;
	
	@Autowired
	private IUserManager userManager;

	@RequestMapping(value = "auth/profile/changePassword")
	public String preparePasswordChange(Principal principal, ModelMap map) {
		map.addAttribute("pw", new PasswordBackingBean());
		return "auth/profile/changePassword";
	}
	
	@RequestMapping(value = "auth/profile/executeChange", method = RequestMethod.POST)
	public String executeChange(@Valid @ModelAttribute("pw") PasswordBackingBean password, BindingResult results, Principal principal, ModelMap map) {
		if (results.hasErrors()) {
			return "auth/profile/changePassword";
		}
		
		String encrypt = userFactory.encrypt(password.getPassword());
		User user = userManager.getUserById(principal.getName());
		user.setPassword(encrypt);
		userManager.saveUser(user);
		return "redirect:/auth/welcome";
	}
}
