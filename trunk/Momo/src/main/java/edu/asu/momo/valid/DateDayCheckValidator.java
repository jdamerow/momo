package edu.asu.momo.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateDayCheckValidator implements ConstraintValidator<DateDayCheck, String> {

	@Override
	public void initialize(DateDayCheck arg0) {
		
	}

	@Override
	public boolean isValid(String arg0,
			ConstraintValidatorContext arg1) {
		if (arg0.trim().isEmpty())
			return true;
		Pattern pattern = Pattern.compile("[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]");
		Matcher match = pattern.matcher(arg0);
		return match.matches();
	}

}
