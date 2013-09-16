package edu.asu.momo.user;

import java.util.List;

import edu.asu.momo.core.Role;

public interface IUserFactory {

	public abstract User createUser(String username, String name, String email,
			String password, List<Role> roles);

	public abstract String encrypt(String pw);

}