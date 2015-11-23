package edu.asu.momo.web.profile.backing;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.momo.valid.PasswordsMatch;

@PasswordsMatch
public class PasswordBackingBean {

	@NotEmpty
	private String password;
	@NotEmpty
	private String control;
	
	private String userid;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
