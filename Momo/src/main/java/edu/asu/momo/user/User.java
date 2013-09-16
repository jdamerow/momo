package edu.asu.momo.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.asu.momo.user.login.MomoGrantedAuthority;

public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2581926783303008251L;
	
	private String username;
	private String name;
	private String email;
	private String password;
	private List<MomoGrantedAuthority> authorities;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  authorities;
	}
	
	public void addAuthority(MomoGrantedAuthority auth) {
		this.authorities.add(auth);
	}
	
	public void setAuthorities(List<MomoGrantedAuthority> authorities)
	{
		this.authorities = authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
