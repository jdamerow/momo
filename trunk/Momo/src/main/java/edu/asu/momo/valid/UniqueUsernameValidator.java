package edu.asu.momo.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.User;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private IUserManager userManager;
	
	@Override
	public void initialize(UniqueUsername arg0) {
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		User user = userManager.getUserById(arg0.trim());
		if (user == null)
			return true;
		return false;
	}

	

}
