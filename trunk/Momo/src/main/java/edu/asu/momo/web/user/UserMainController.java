package edu.asu.momo.web.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.momo.core.Role;
import edu.asu.momo.db.IRoleManager;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.User;
import edu.asu.momo.web.user.backing.UserForm;

@Controller
public class UserMainController {

	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private IRoleManager roleManager;
	
	@RequestMapping(value = "auth/user/manage", method = RequestMethod.GET)
	public String getAllUsers(ModelMap map) {
		List<User> users = userManager.getAllUsers();
		map.put("users", createBackingObjects(users));
		return "auth/user/manage";
	}
	
	protected List<UserForm> createBackingObjects(List<User> users) {
		List<UserForm> userObjs = new ArrayList<UserForm>();
		
		for (User user : users) {
			UserForm uf = new UserForm();
			uf.setName(user.getName());
			uf.setEmail(user.getEmail());
			uf.setUsername(user.getUsername());
			
			uf.setRoles(new ArrayList<Role>());
			for (GrantedAuthority auth : user.getAuthorities()) {
				Role role = roleManager.getRole(auth.getAuthority());
				uf.getRoles().add(role);				
			}
			
			userObjs.add(uf);
		}
		
		return userObjs;
	}
	
}
