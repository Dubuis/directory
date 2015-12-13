package directory.business.dao.jdbc.beanConverter;

import java.sql.SQLException;

interface IResultSetToBean<T> {
   public T toBean(java.sql.ResultSet rs) throws SQLException;
}