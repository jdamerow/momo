package edu.asu.momo.web.user.backing;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.core.Role;
import edu.asu.momo.valid.UniqueUsername;

public class UserForm {

	@NotEmpty(message = "Please provide a username.")
	@UniqueUsername
	private String username;
	
	@NotEmpty(message = "Please provide name of user.")
	private String name;
	
	@NotEmpty(message = "Please enter a password.")
	private String password;
	
	@NotEmpty(message = "Please provide an email address.")
	private String email;
	
	@NotEmpty(message = "At least one role needs to be selected.")
	private List<Role> roles;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
