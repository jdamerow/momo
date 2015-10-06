package edu.asu.momo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Role;
import edu.asu.momo.user.login.MomoGrantedAuthority;

@Service
public class UserFactory implements IUserFactory {

	/* (non-Javadoc)
	 * @see edu.asu.momo.user.IUserFactory#createUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public User createUser(String username, String name, String email, String password, List<Role> roles) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		user.setAuthorities(new ArrayList<MomoGrantedAuthority>());
		for (Role role : roles) {
			user.addAuthority(new MomoGrantedAuthority(role.getId()));
		}
		
		return user;
	}

	/* (non-Javadoc)
	 * @see edu.asu.momo.user.IUserFactory#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt());
	}
}
