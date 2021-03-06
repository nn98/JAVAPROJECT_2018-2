import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Create {

	public static void main(String[] args) {
		String sql;
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Y_Home", "q192837q*");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			
			st.executeQuery("use 6202_pc;");
			for(int i=1;i<43;i++) {
				sql = "create table pc"+String.format("%02d", i)+" (" + 
						"PC_NUMBER int(3) unsigned not null," + 
						"PC_STATUS int(1) unsigned not null," + 
						" primary key (PC_NUMBER)" + 
						" );";    //insert시 인자값을 넣기 위해 사용
				PreparedStatement pst = con.prepareStatement(sql);
				pst.execute();
				pst.close();
			}


		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}