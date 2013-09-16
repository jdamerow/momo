package edu.asu.momo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.asu.momo.core.Team;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.teams.TeamTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

/**
 * Converts a username into a {@link UserBackingBean}.
 * 
 * @author Julia Damerow
 *
 */
public class TeamBackingBeanConverter implements Converter<String, TeamBackingBean> {

	@Autowired
	private ITeamsManager manager;
	
	@Autowired
	private TeamTranslator translater;
	
	@Override
	public TeamBackingBean convert(String source) {
		if (source == null)
			return null;
		Team team = manager.getTeam(source);
		return translater.translateTeam(team);
	}


}
