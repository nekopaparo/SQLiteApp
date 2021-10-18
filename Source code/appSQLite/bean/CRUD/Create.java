package bean.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Create {
	
	private String sql = "INSERT INTO city";
	private String names = "";
	private String values = "";
	public Create(Connection conn, Object[] name, Object[] value) throws SQLException {
		for(int i=0; i<name.length; i++) {
			if(i!=0) {
				names += ", ";
				values += ", ";
			}
			names += "'" + name[i] + "'";
			values += "'" + value[i] + "'";
		}
		sql += "(" + names + ") VALUES(" + values + ")";
		Statement sm = conn.createStatement();
		sm.executeUpdate(sql);
		sm.close();
	}
}
