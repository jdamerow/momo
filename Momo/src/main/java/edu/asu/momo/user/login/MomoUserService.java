package edu.asu.momo.user.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.asu.momo.db.IUserManager;

@Service(value = "momoUserService")
public class MomoUserService implements UserDetailsService {

	@Autowired
	private IUserManager userManager;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails matchingUser = userManager.getUserById(username);

		if (matchingUser == null) {
			throw new UsernameNotFoundException("Wrong username or password");
		}

		return matchingUser;
	}

}
