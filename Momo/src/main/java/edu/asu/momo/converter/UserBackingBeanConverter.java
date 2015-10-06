package edu.asu.momo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.User;
import edu.asu.momo.user.UserTranslator;
import edu.asu.momo.web.user.backing.UserBackingBean;

/**
 * Converts a username into a {@link UserBackingBean}.
 * 
 * @author Julia Damerow
 *
 */
public class UserBackingBeanConverter implements Converter<String, UserBackingBean> {

	@Autowired
	private IUserDBManager manager;
	
	@Autowired
	private UserTranslator translater;
	
	@Override
	public UserBackingBean convert(String source) {
		User user = manager.getUserById(source);
		return translater.translateUser(user);
	}


}
