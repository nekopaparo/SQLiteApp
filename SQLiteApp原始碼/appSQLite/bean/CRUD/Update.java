package bean.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
	
	private String sql = "UPDATE city SET ";
	
	public Update(Connection conn, Object[] name, Object[] value) throws SQLException {
		for(int i=1; i<name.length; i++) {
			if(i!=1) sql += ", ";
			sql += name[i] + " = '" + value[i] + "' ";
		}
		sql += "WHERE ID = " + value[0];
		Statement sm = conn.createStatement();
		sm.executeUpdate(sql);
		sm.close();
	}

}
