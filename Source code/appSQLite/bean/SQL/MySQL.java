package bean.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	private Connection conn = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	private String path = "jdbc:mysql://localhost/world?";
	private String value = "serverTimezon=GMT&useSSL=false&";
	
	private String user = "root";
	private String passwd = "1234";
	
	public Connection getCon() {
		try {
			Class.forName(driver);
			System.out.println("MySQL driver is OK");
			conn = DriverManager.getConnection(path+value, user, passwd);
			System.out.println("MySQL 連線成功");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL driver is not OK");
		} catch (SQLException e) {
			System.out.println("MYSQL 連線失敗");
		}
		return conn;
	}
}
