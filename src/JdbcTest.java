import java.sql.*;
public class JdbcTest {
	public static void main(String[] args) {
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://172.16.4.206:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"notting", "testing00");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			rs = st.executeQuery("SHOW DATABASES");
			if (st.execute("SHOW DATABASES")) {
				rs = st.getResultSet();
			}
			while (rs.next()) {
				String str = rs.getNString(1);
				System.out.println(str);
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
}