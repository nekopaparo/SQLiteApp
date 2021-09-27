package bean.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	private String sql = "DELETE FROM city WHERE ID = ";
	
	public Delete(Connection conn, Object ID) throws SQLException {
		sql += ID;
		Statement sm = conn.createStatement();
		sm.executeUpdate(sql);
		sm.close();
	}
}
