package edu.asu.momo.web.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.IUserFactory;
import edu.asu.momo.user.User;
import edu.asu.momo.web.profile.backing.PasswordBackingBean;

@Controller
public class UserPasswordChangeController {
	
	@Autowired
	private IUserFactory userFactory;
	
	@Autowired
	private IUserDBManager userManager;

	@RequestMapping(value = "auth/user/changePassword/{userId}")
	public String preparePasswordChange(Principal principal, ModelMap map, @PathVariable String userId) {
		map.addAttribute("pw", new PasswordBackingBean());
		map.addAttribute("userId", userId);
		return "auth/user/changePassword";
	}
	
	@RequestMapping(value = "auth/user/executeChange", method = RequestMethod.POST)
	public String executeChange(@Valid @ModelAttribute("pw") PasswordBackingBean password, BindingResult results, Principal principal, ModelMap map) {
		if (results.hasErrors()) {
			return "auth/user/changePassword";
		}
		
		String encrypt = userFactory.encrypt(password.getPassword());
		User user = userManager.getUserById(password.getUserid());
		user.setPassword(encrypt);
		userManager.updateUser(user);
		return "redirect:/auth/user/manage";
	}
}
