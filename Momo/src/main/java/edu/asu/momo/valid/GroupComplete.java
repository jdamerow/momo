package edu.asu.momo.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupComplete {

	public abstract String message() default "";
	public int groupId();
	public abstract Class[] groups() default {};
	public abstract Class[] payload() default {};
}
