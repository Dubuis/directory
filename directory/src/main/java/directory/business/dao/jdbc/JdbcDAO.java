package directory.business.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import directory.business.dao.IDao;
import directory.business.dao.jdbc.beanConverter.ResultSetToGroup;
import directory.business.dao.jdbc.beanConverter.ResultSetToPerson;
import directory.model.Group;
import directory.model.Person;

public class JdbcDAO implements IDao {
	@Autowired
	private IConnector connector;
	
	/**
	 * Empty Constructor
	 */
	public JdbcDAO() {
		connector.init();
		String querySql;
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			// Person table creation
			querySql = "CREATE TABLE IF NOT EXISTS `" + Person.TABLE + "` ("
					+ "`" + Person.ID + "` bigint(20) NOT NULL,"
					+ "`" + Person.FAMILYNAME + "` varchar(100) NOT NULL,"
					+ "`" + Person.FIRSTNAME + "` varchar(100) NOT NULL,"
	  				+ "`" + Person.EMAIL + "` varchar(100) DEFAULT NULL,"
	  				+ "`" + Person.WEBSITE + "` varchar(100) DEFAULT NULL,"
	  				+ "`" + Person.BIRTHDATE + "` varchar(50) DEFAULT NULL,"
	  				+ "`" + Person.PASSWORD + "` varchar(100) NOT NULL,"
	  				+ "PRIMARY KEY (`" + Person.ID + "`)"
	  				+ ");";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Group table creation
			querySql = "CREATE TABLE IF NOT EXISTS `" + Group.TABLE + "` ("
					+ "`" + Group.ID + "` bigint(20) NOT NULL,"
					+ "`" + Group.NAME + "` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`" + Group.ID + "`)"
					+ ");";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Link table creation
			querySql = "CREATE TABLE IF NOT EXISTS `" + Group.LINK_TABLE + "` ("
					+ "`" + Group.ID_GROUP + "` bigint(20) NOT NULL,"
					+ "`" + Group.ID_PERSON + "` bigint(20) NOT NULL,"
					+ "UNIQUE KEY `unique_index` "
					+ "(`" + Group.ID_GROUP + "`,`" + Group.ID_PERSON + "`),"
					+ "FOREIGN KEY "
					+ "(`" + Group.ID_GROUP + "`) REFERENCES `" + Group.TABLE + "`(`" + Group.ID + "`),"
					+ "FOREIGN KEY "
					+ "(`" + Group.ID_PERSON + "`) REFERENCES `" + Person.TABLE + "`(`" + Person.ID + "`)"
					+ ");";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Group table insertion
			querySql = "INSERT INTO `" + Group.TABLE + "` (`"
					+ Group.ID + "`, `" + Group.NAME + "`) VALUES"
					+ "(1, 'FSIL'),"
					+ "(2, 'ISL'),"
					+ "(3, 'FSI')"
					+ "ON DUPLICATE KEY;";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		
	}
	
	public void close() {
		connector.close();
	}
	
	public List<Person> findAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		String querySql = "SELECT * FROM " + Person.TABLE;
		Statement statement;
		ResultSet rs;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
			rs = statement.getResultSet();
			while(rs.next()) {
				persons.add(ResultSetToPerson.toBean(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public List<Person> findAllPersonsInGroup(long groupId) {
		List<Long> personsId = new ArrayList<Long>();
		List<Person> persons = new ArrayList<Person>();
		String querySql;
		Statement statement;
		ResultSet rs;
		try(Connection connection = connector.newConnection()) {
			querySql = "SELECT " + Group.ID_PERSON + " FROM " + Group.LINK_TABLE +
					" WHERE " + Group.ID_GROUP + "='" + groupId + "'";
			statement = connection.createStatement();
			statement.execute(querySql);
			rs = statement.getResultSet();
			while(rs.next()) {
				Long id = rs.getLong(Group.ID_PERSON);
				personsId.add(id);
			}
			if(personsId.isEmpty()) {
				return null;
			}
			for(Long id : personsId) {
				persons.add(findPerson(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	public Person findPerson(long id) {
		Person person = null;
		String querySql = "SELECT * FROM " + Person.TABLE + " WHERE "
				+ Person.ID + "='" + id + "'";
		Statement statement;
		ResultSet rs;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
			rs = statement.getResultSet();
			if(rs.next()) {
				person = ResultSetToPerson.toBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	public void savePerson(Person p) {
		String querySql =
				"INSERT INTO " + Person.TABLE + "( " +
				Person.ID + ", " + Person.FAMILYNAME + ", " +
				Person.FIRSTNAME + ", " + Person.EMAIL + ", " +
				Person.WEBSITE + ", " + Person.BIRTHDATE+ ", " +
				Person.PASSWORD + ") VALUES ('" +
				p.getId() + "', '" + p.getFamilyName() + "', '" +
				p.getFirstName() + "', '" + p.getEmail() + "', '" +
				p.getWebSite() + "', '" + p.getBirthDate() + "', '" +
				p.getPassword() + "')" +
				" ON DUPLICATE KEY UPDATE " +
				Person.FAMILYNAME + "='" + p.getFamilyName() + "', " +
				Person.FIRSTNAME + "='" + p.getFirstName() + "', " +
				Person.EMAIL + "='" + p.getEmail() +  "', " +
				Person.WEBSITE + "='" + p.getWebSite() + "', " +
				Person.BIRTHDATE + "='" + p.getBirthDate() + "', " +
				Person.PASSWORD + "='" + p.getPassword() + "'";
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removePerson(long id) {
		String querySql;
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			// Remove Person from Person table
			querySql = "DELETE FROM " + Person.TABLE
					+ " WHERE " + Person.ID + "='" + id + "'";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Remove Person id from Links Table
			querySql = "DELETE FROM " + Group.LINK_TABLE
					+ " WHERE " + Group.ID_PERSON + "='" + id + "'";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Group> findAllGroups() {
		List<Group> groups = new ArrayList<Group>();
		String querySql = "SELECT * FROM " + Group.TABLE;
		Statement statement;
		ResultSet rs;
		try(Connection connection = connector.newConnection()) {
			// Load all groups (without List)
			statement = connection.createStatement();
			statement.execute(querySql);
			rs = statement.getResultSet();
			while(rs.next()) {
				groups.add(ResultSetToGroup.toBean(rs));
			}
			// Set list in all groups
			for(Group group : groups) {
				List<Person> persons = findAllPersonsInGroup(group.getId());
				group.setList(persons);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}

	
	public Group findGroup(long id) {
		Group group = null;
		String querySql = "SELECT * FROM " + Group.TABLE + " WHERE "
				+ Group.ID + "='" + id + "'";
		Statement statement;
		ResultSet rs;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
			rs = statement.getResultSet();
			if(rs.next()) {
				group = ResultSetToGroup.toBean(rs);
				List<Person> persons = findAllPersonsInGroup(id);
				group.setList(persons);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}
	
	public void saveGroup(Group g) {
		String querySql = null;
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			// Update Group table
			querySql = "INSERT INTO " + Group.TABLE + "("
					+ Group.ID + ", " + Group.NAME + ") VALUES ("
					+ g.getId() + ", '" + g.getName() + "')"
					+ " ON DUPLICATE KEY UPDATE "
					+ Group.NAME + "='" + g.getName() +"'";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Update link table
			for(Person person : g.getList()) {
				querySql = "INSERT INTO " + Group.LINK_TABLE + "("
						+ Group.ID_GROUP + "," + Group.ID_PERSON + ") VALUES ("
						+ g.getId() + "," + person.getId() + ")";
				statement = connection.createStatement();
				statement.execute(querySql);
				statement.close();
			}
		} catch (SQLException e) {
			System.out.println(querySql);
			e.printStackTrace();
		}
	}
	
	public void removeGroup(long id) {
		String querySql;
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			// Remove Group from Group table
			querySql = "DELETE FROM " + Group.TABLE
					+ " WHERE " + Group.ID + "='" + id + "'";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
			
			// Remove Group id from Links Table
			querySql = "DELETE FROM " + Group.LINK_TABLE
					+ " WHERE " + Group.ID_GROUP + "='" + id + "'";
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removePersonFromGroup(long idPerson, long idGroup) {
		String querySql = "INSERT INTO " + Group.LINK_TABLE + "("
				+ Group.ID_GROUP + "," + Group.ID_PERSON + ") VALUES ("
				+ idGroup + "," + idPerson + ")";
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPersonInGroup(long idPerson, long idGroup) {
		String querySql = "DELETE FROM " + Group.LINK_TABLE 
				+ " WHERE " + Group.ID_GROUP + "='" + idGroup + "'"
				+ " AND " + Group.ID_PERSON + "='" + idPerson + "'";
		Statement statement;
		try(Connection connection = connector.newConnection()) {
			statement = connection.createStatement();
			statement.execute(querySql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
