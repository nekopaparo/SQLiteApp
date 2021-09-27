package bean.SQLite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bean.SQL.SQLite;

public class CreateValue {

	public static void main(String[] args) throws SQLException {
		Connection sqliteConn = null;
		Statement sqlitesm = null;
		try {
			String sql = "INSERT INTO user VALUES('root', '123456789')";
			//String sql = "DROP TABLE user"; //刪除表格
			SQLite sqlite = new SQLite();
			sqliteConn = sqlite.getCon();
			sqliteConn.setAutoCommit(false);
			sqlitesm = sqliteConn.createStatement();
			sqlitesm.executeUpdate(sql);
			
			/*city資料從MySQL複製
			String sql = "INSERT INTO city ";
			MySQL mysql = new MySQL();
			Connection mysqlConn = mysql.getCon();
			Statement sm = mysqlConn.createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM city  LIMIT 1000");
			for (int i = 0; i < 10; i++) {
				rs.next();
			}
			while (rs.next()) {
				String value = "VALUES (";
				for (int i = 1; i < 6; i++) {
					if (i != 1)
						value += ", ";
					if (i != 1 && i != 5)
						value += "'";
					value += rs.getString(i);
					if (i != 1 && i != 5)
						value += "'";
				}
				value += ");";
				sqlitesm.executeUpdate(sql + value);
			}
			sm.close();
			mysqlConn.close();
			 */
			sqliteConn.commit();
			System.out.println("新增成功");
		} catch (SQLException e) {
			if (sqliteConn != null) {
				sqliteConn.rollback();
			}
			System.out.println("新增失敗");
			e.printStackTrace();
		} finally {
			if (sqliteConn != null) {
				sqlitesm.close();
				sqliteConn.close();
			}
		}

	}

}
