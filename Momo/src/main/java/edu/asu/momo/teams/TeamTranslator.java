package edu.asu.momo.teams;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.teams.backing.TeamBackingBean;
import edu.asu.momo.web.user.backing.UserBackingBean;

/**
 * Translate a given {@link Team} or {@link TeamBackingBean} into
 * the other type.
 * 
 * @author Julia Damerow
 *
 */
@Service
public class TeamTranslator {

	@Autowired
	private IUserDBManager userManager;
	
	@Autowired
	private UserTranslator userTranslator;
	
	/**
	 * Translate a {@link Team} object into {@link TeamBackingBean}.
	 * 
	 * @param team
	 * @return
	 */
	public TeamBackingBean translateTeam(Team team) {
		TeamBackingBean bean = new TeamBackingBean();
		bean.setName(team.getName());
		bean.setDescription(team.getDescription());
		bean.setManagers(new ArrayList<UserBackingBean>());
		bean.setMembers(new ArrayList<UserBackingBean>());
		bean.setId(team.getId());
		
		if (team.getManagers() != null) {
			for (String managerId : team.getManagers()) {
				User user = userManager.getUserById(managerId);
				if (user != null) {
					UserBackingBean ub = userTranslator.translateUser(user);
					if (ub != null)
						bean.getManagers().add(ub);
				}
			}
		}
		if (team.getMembers() != null) {
			for (String managerId : team.getMembers()) {
				User user = userManager.getUserById(managerId);
				if (user != null) {
					UserBackingBean ub = userTranslator.translateUser(user);
					if (ub != null)
						bean.getMembers().add(ub);
				}
			}
		}
		
		return bean;
	}
	
	protected void setValues(Team team, TeamBackingBean backingBean) {
		team.setDescription(backingBean.getDescription());
		team.setName(backingBean.getName());
		
		team.setManagers(new ArrayList<String>());
		team.setMembers(new ArrayList<String>());
		
		if (backingBean.getManagers() != null) {
			for (UserBackingBean ub : backingBean.getManagers()) {
				team.getManagers().add(ub.getUsername());
			}
		}
		
		if (backingBean.getMembers() != null) {
			for (UserBackingBean ub : backingBean.getMembers()) {
				team.getMembers().add(ub.getUsername());
			}
		}
	}
	
	public void updateTeam(Team team, TeamBackingBean teamBackingBean) {
		setValues(team, teamBackingBean);
	}
	
	/**
	 * Translates a {@link TeamBackingBean} into a {@link Team} object.
	 * @param backingBean BackingBean to translate.
	 * @return
	 */
	public Team translate(TeamBackingBean backingBean) {
		Team team = new Team();
		team.setId(backingBean.getId());
		setValues(team, backingBean);
		
		return team;
	}
}
