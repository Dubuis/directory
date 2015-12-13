package directory.business.dao.jdbc.beanConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import directory.model.Person;

public class ResultSetToPerson {
	public static Person toBean(ResultSet rs) throws SQLException {
		Person p = new Person();
		p.setId(rs.getLong(Person.ID));
		p.setFamilyName(rs.getString(Person.FAMILYNAME));
		p.setFirstName(rs.getString(Person.FIRSTNAME));
		p.setEmail(rs.getString(Person.EMAIL));
		p.setWebSite(rs.getString(Person.WEBSITE));
		p.setBirthDate(rs.getDate(Person.BIRTHDATE));
		p.setPassword(rs.getString(Person.PASSWORD));
		return p;
	}
}
