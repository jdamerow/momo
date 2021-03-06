package edu.asu.momo.web.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.db.IRoleManager;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.IUserFactory;
import edu.asu.momo.user.User;
import edu.asu.momo.web.user.backing.UserBackingBean;

@Controller
public class AddUserController {

	@Autowired
	private IUserManager userManager;
	
	@Autowired 
	private IUserFactory userFactory;
	
	@Autowired
	private IRoleManager roleManager;
	
	@RequestMapping(value = "auth/user/showAddUser", method = RequestMethod.GET)
	public String showAddUser(ModelMap map) {
		map.addAttribute("availableRoles", roleManager.getRoles());
		map.addAttribute("userBackingBean", new UserBackingBean());
		
		return "auth/user/showAddUser";
	}
	
	@RequestMapping(value = "auth/user/addUser", method = RequestMethod.POST)
	public String addNewUser(@Valid @ModelAttribute UserBackingBean userForm, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			map.addAttribute("availableRoles", roleManager.getRoles());
			
			return "auth/user/showAddUser";
		}
		User user = userFactory.createUser(userForm.getUsername(), userForm.getName(), userForm.getEmail(), userForm.getPassword(), userForm.getRoles());
		userManager.saveUser(user);
		
		return "redirect:/auth/user/manage";
	}
	

}
