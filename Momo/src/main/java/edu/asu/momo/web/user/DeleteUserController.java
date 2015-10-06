package edu.asu.momo.web.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;

@Controller
public class DeleteUserController {
	
	@Autowired
	private IUserDBManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;

	@RequestMapping(value = "auth/user/delete/{username}")
	public String prepareDelete(Principal principal, @PathVariable("username") String username, ModelMap map) {
		if (username == null || username.trim().isEmpty())
			return "redirect:/auth/user/manage";
		
		User user = userManager.getUserById(username);
		if (user == null) {
			return "redirect:/auth/user/manage";
		}
		
		map.addAttribute("user", userTranslator.translateUser(user));
		return "auth/user/deleteUser";
	}
	
	@RequestMapping(value = "auth/user/executeDelete/{username}")
	public String executeDelete(Principal principal, @PathVariable("username") String username, ModelMap map) {
		if (username == null || username.trim().isEmpty())
			return "redirect:/auth/user/manage";
		
		User user = userManager.getUserById(username);
		if (user == null) {
			return "redirect:/auth/user/manage";
		}
		
		userManager.deleteUser(username);
		return "redirect:/auth/user/manage";
	}
	
}
