package directory.business.dao.jdbc.beanConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import directory.model.Group;

public class ResultSetToGroup {
	public static Group toBean(ResultSet rs) throws SQLException {
		Group g = new Group();
		g.setId(rs.getLong(Group.ID));
		g.setName(rs.getString(Group.NAME));
		return g;
	}

}
