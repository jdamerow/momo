package edu.asu.momo.db.impl;

import edu.asu.momo.user.User;

public interface ILoginManager {

	public abstract User getUserById(String username);

}