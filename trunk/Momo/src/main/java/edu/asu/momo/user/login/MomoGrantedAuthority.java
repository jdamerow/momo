package edu.asu.momo.user.login;

import org.springframework.security.core.GrantedAuthority;

public class MomoGrantedAuthority implements GrantedAuthority {

	private String roleName;
	
	public MomoGrantedAuthority(String name) {
		this.roleName = name;
	}
	
	public MomoGrantedAuthority() { }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 711167440813692597L;

	@Override
	public String getAuthority() {
		return roleName;
	}

	public void setAuthority(String rolename) {
		this.roleName = rolename;
	}
}
