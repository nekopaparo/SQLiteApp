package bean.SQLite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bean.SQL.SQLite;

public class CreateTable {

	public static void main(String[] args) throws SQLException{
		Connection conn = null;
		Statement sm = null;
		/*使用者資料 user不重複*/
		String sql = "CREATE TABLE user ("
				   + " 	user 	VARCHER(20) PRIMARY KEY NOT NULL, "
				   + " 	passwd  VARCHER(20) 			NOT NULL"
				   + ")";
		/* city
		String sql = "CREATE TABLE city ("
				   + "	ID			INT			ZEROFILL KEY NOT NULL, "
				   + " 	Name		VARCHAR(20) 			 NOT NULL, "
				   + "  CountryCode VARCHAR(3), "
				   + "  District	VARCHAR(20), "
				   + "  Population	INT(10) "
				   + ")";
		*/
		try {
			SQLite sqlite = new SQLite();
			conn = sqlite.getCon();
			sm = conn.createStatement();
			sm.executeUpdate(sql);
			System.out.println("Table 建立成功");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table 建立失敗");
		}
		finally {
			sm.close();
			conn.close();
		}
		
	}

}
