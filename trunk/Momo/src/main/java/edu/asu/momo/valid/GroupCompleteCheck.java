package edu.asu.momo.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = GroupCompleteValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupCompleteCheck {

	public abstract String message() default "";
	public String[] groupMessages();
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
