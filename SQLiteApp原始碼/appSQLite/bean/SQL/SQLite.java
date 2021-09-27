package bean.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
	private Connection con = null;

	private String driver = "org.sqlite.JDBC";
	private String path = "jdbc:sqlite:";
	private String dbPath = "data/world.db";

	public Connection getCon() throws SQLException{
		try {
			Class.forName(driver);
			System.out.println("SQLite driver is OK");
			con = DriverManager.getConnection(path + dbPath);
			System.out.println("SQLite 連線成功");
		} catch (ClassNotFoundException e) {
			System.out.println("SQLite driver is not OK");
		} catch (SQLException e) {
			System.out.println("SQLite 連線失敗");
			throw new SQLException(); 
		}
		return con;
	}
}
