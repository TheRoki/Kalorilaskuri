import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Roope
 * @version 1.00 2017/4/25
 *
 */
public class DBconnect {

	public static Connection dbConnector() {
		try {
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/ravinto";
			String user = "root";
			String password = "password";
			conn  = DriverManager.getConnection(url, user, password);
			return conn;
		} catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
}
