package bean.CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read {
	
	private String sql = "SELECT * FROM city WHERE ";
	
	private Connection conn;
	
	public Read(Connection conn, Object[] name, Object[] value){
		for(int i=0; i<name.length; i++) {
			if(i!=0) sql += "AND ";
			sql += name[i] + " LIKE '%" + value[i] + "%'";
		}
		this.conn = conn;
	}
	public ResultSet getRS() throws SQLException {
		Statement sm = conn.createStatement();
		if(sm.executeQuery(sql).next()) {
			return sm.executeQuery(sql + "ORDER BY ID ASC");
		}
		else return null;
	}
}
