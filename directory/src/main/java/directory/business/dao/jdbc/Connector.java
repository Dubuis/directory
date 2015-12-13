package directory.business.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("directoryApp.business.dao")
public class Connector implements IConnector {
	/*
	 * Here are parameters for connection
	 * TODO autowire parameters for connection with bean(s)
	 */
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/directory_app";
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	
	private BasicDataSource dataSource;
	
	/**
	 * Empty Constructor
	 */
	public Connector() {
		dataSource = new BasicDataSource();
		dataSource.setUrl(Connector.URL);
		dataSource.setDriverClassName(Connector.DRIVER_CLASS_NAME);
		dataSource.setUsername(Connector.USERNAME);
		dataSource.setPassword(Connector.PASSWORD);
	}
	
	/**
	 * Return the BasicDataSource
	 * @return
	 */
	public BasicDataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * Get the Url of Data Base
	 * @return URL
	 */
	public String getUrl() {
		return dataSource.getUrl();
	}
	
	/**
	 * Get the password to connect at Data Base
	 * @return password
	 */
	public String getPassword() {
		return dataSource.getPassword();
	}
	
	/**
	 * Get the login to connect at Data Base
	 * @return
	 */
	public String getUsername() {
		return dataSource.getUsername();
	}
	
	/**
	 * Get the driver name for the Data Base
	 * @return
	 */
	public String getDriverName() {
		return dataSource.getDriverClassName();
	}
	
	public int getNumActive() {
		return dataSource.getNumActive();
	}
	
	public int getNumIdle() {
		return dataSource.getNumIdle();
	}
	
	//@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Set the url of Data Base
	 * @param url
	 */
	public void setUrl(String url) {
		dataSource.setUrl(url);
	}
	
	/**
	 * Set the password to connect at Data Base
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		dataSource.setPassword(pwd);
	}
	
	/**
	 * Set the login to connect at Data Base
	 * @param username
	 */
	public void setUsername(String username) {
		dataSource.setUsername(username);
	}
	
	/**
	 * Set the driver name for Data Base
	 * @param driverName
	 */
	public void setDriverName(String driverName) {
		dataSource.setDriverClassName(driverName);
	}
	
	/**
	 * Init the Service
	 */
	public void init() {
		if (getUsername() == null ||
				getPassword() == null ||
				getUrl() == null ||
				getDriverName() == null) {
			throw new IllegalStateException("One or more parameter is null");
		}
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(5);
	}
	
	/**
	 * Close the Service
	 */
	public void close() {}
	
	/**
	 * Create a new Connection
	 * @return Connection created
	 * @throws SQLException
	 */
	public Connection newConnection() throws SQLException {
		Connection connection = null;
		connection = dataSource.getConnection();
		return connection;
	}
	
	/**
	 * Close properly the connection
	 * @param c - the connection to close
	 */
	public void quietClose(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
