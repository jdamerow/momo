package edu.asu.momo.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Constraint(validatedBy = TeamIsProvidedValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TeamProvided {
	public abstract String message() default "Team of project needs to be specified.";
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
