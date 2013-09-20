package edu.asu.momo.db;

import com.db4o.ObjectContainer;

/**
 * Interface to be implemented by Db4o database connection manager.
 * 
 * @author Julia Damerow
 *
 */
public interface IDatabaseManager {

	public abstract ObjectContainer getClient();

	public abstract boolean isEncrypt();

	public abstract void setEncrypt(boolean encrypt);

}