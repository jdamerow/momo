package edu.asu.momo.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.asu.momo.web.profile.backing.PasswordBackingBean;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, PasswordBackingBean> {

	@Override
	public void initialize(PasswordsMatch arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(PasswordBackingBean arg0,
			ConstraintValidatorContext arg1) {
		return (arg0.getPassword().equals(arg0.getControl()));
	}

}
