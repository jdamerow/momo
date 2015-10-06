package edu.asu.momo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.user.User;

/**
 * Converts a username into a {@link User} object.
 * 
 * @author Julia Damerow
 *
 */
public class UserStringConverter implements Converter<String, User> {

	@Autowired
	private IUserDBManager manager;
	
	@Override
	public User convert(String arg0) {
		return manager.getUserById(arg0);
	}


}
