package bean.SQLite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import bean.SQL.SQLite;

public class ShowData {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement sm = null;
		try {
			SQLite sl = new SQLite();
			conn = sl.getCon();
			sm = conn.createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM city");
			// 標題
			ResultSetMetaData rsmd = rs.getMetaData();
			int dataCount = rsmd.getColumnCount();
			for (int i = 1; i <= dataCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			// 內容
			while (rs.next()) {
				for (int i = 1; i <= dataCount; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("資料顯示失敗");
		} finally {
			if(conn != null) {
				sm.close();
				conn.close();
			}
		}
	}

}
