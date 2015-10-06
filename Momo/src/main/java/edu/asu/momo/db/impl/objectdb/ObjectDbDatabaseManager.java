package edu.asu.momo.db.impl.objectdb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.db.IDatabaseManager;

@Component
public class ObjectDbDatabaseManager implements IDatabaseManager {

	@PersistenceContext 
	protected EntityManager manager;
	
	@Transactional
	public void update(Object object) {
		manager.merge(object);
	}
	
	@Transactional
	public void store(Object object) {
		manager.persist(object);
		manager.flush();
	}
	
	@Override
	@Transactional
	public Object get(String id, Class<?> clazz) {
		Object object = manager.find(clazz, id);
		return object;
	}
	
	@Override
	@Transactional
	public boolean delete(String id, Class<?> clazz) {
		Object obj = manager.find(clazz, id);
		manager.remove(obj);
		return true;
	}
	
	@Override
	public EntityManager getManager() {
		return manager;
	}
}
