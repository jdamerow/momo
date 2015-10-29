package edu.asu.momo.db;

import javax.persistence.EntityManager;


/**
 * Interface to be implemented by Db4o database connection manager.
 * 
 * @author Julia Damerow
 *
 */
public interface IDatabaseManager {

	public void update(Object object);

	public abstract void store(Object object);

	public abstract EntityManager getManager();

	public abstract boolean delete(Object id, Class<?> clazz);

	public abstract Object get(String id, Class<?> clazz);
}