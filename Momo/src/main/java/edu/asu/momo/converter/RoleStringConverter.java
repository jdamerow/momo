package edu.asu.momo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.asu.momo.core.Role;
import edu.asu.momo.db.IRoleManager;

public class RoleStringConverter implements Converter<String, Role> {

	@Autowired
	private IRoleManager manager;
	
	@Override
	public Role convert(String arg0) {
		return manager.getRole(arg0);
	}


}
