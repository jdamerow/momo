package edu.asu.momo.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeCheckValidator implements ConstraintValidator<DateTimeCheck, String> {

	@Override
	public void initialize(DateTimeCheck arg0) {
		
	}

	@Override
	public boolean isValid(String arg0,
			ConstraintValidatorContext arg1) {
		if (arg0.trim().isEmpty())
			return true;
		
		Pattern pattern = Pattern.compile("[0-9][0-9]\\:[0-9][0-9] *(AM|PM)");
		Matcher match = pattern.matcher(arg0);
		return match.matches();
	}

}
