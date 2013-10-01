package edu.asu.momo.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = DateDayCheckValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDayCheck {

	public abstract String message() default "Please use the following format: MM/DD/YYYY.";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
