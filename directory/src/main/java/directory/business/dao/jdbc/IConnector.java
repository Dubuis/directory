package directory.business.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnector {
	public void init();
	public void close();
	public Connection newConnection() throws SQLException;
	public void quietClose(Connection c);
}
