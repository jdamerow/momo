package edu.asu.momo.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.asu.momo.web.teams.backing.TeamBackingBean;

public class TeamIsProvidedValidator implements ConstraintValidator<TeamProvided, TeamBackingBean> {

	@Override
	public void initialize(TeamProvided arg0) {
		
	}

	@Override
	public boolean isValid(TeamBackingBean arg0, ConstraintValidatorContext arg1) {
		return arg0 != null;
	}

	

}
