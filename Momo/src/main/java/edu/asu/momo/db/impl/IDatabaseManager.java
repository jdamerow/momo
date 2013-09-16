package edu.asu.momo.db.impl;

import com.db4o.ObjectContainer;

public interface IDatabaseManager {

	public abstract ObjectContainer getClient();

	public abstract boolean isEncrypt();

	public abstract void setEncrypt(boolean encrypt);

}