package edu.asu.momo.db.impl;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;

import edu.asu.momo.core.Team;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.user.User;

@Component
@PropertySource(value = "classpath:/db4o.properties")
public class Db4oDatabaseManager implements Serializable, IDatabaseManager {

	@Autowired
	private Environment env;
	
	private ObjectContainer client;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3325272288078647257L;
	private ObjectServer server;
	private boolean encrypt = true;

	@PostConstruct
	public synchronized void init() {
		close();
		ServerConfiguration configuration = Db4oClientServer
				.newServerConfiguration();
		configuration.file().blockSize(80);
		String dbfolder = env.getProperty("dbpath_folder");
		if (!dbfolder.endsWith(File.separator))
			dbfolder = dbfolder + File.separator;
		String dbpath = dbfolder + env.getProperty("dbname");
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().messageLevel(1);
		config.common().objectClass(User.class).objectField("username")
				.indexed(true);
		config.common().objectClass(Team.class).objectField("name")
				.indexed(true);
		config.common().objectClass(Team.class).objectField("id").indexed(true);
		config.common().objectClass(User.class).cascadeOnActivate(true);
		config.common().objectClass(User.class).cascadeOnUpdate(true);
		server = Db4oClientServer.openServer(configuration, dbpath, 0);
		client = server.openClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.asu.momo.db.impl.IDatabaseManager#getClient()
	 */
	@Override
	public ObjectContainer getClient() {
		return client;
	}

	private synchronized void close() {
		if (client != null) {
			client.close();
			client = null;
		}
		if (server != null) {
			server.close();
		}
		server = null;
	}

	@PreDestroy
	public void shutdown() {
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.asu.momo.db.impl.IDatabaseManager#isEncrypt()
	 */
	@Override
	public boolean isEncrypt() {
		return encrypt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.asu.momo.db.impl.IDatabaseManager#setEncrypt(boolean)
	 */
	@Override
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
}
