package JavaProject_Final_DesktopClinet;

import java.sql.Connection;
import java.sql.DriverManager;

public class FirstCreate {
	public static void main(String[] args) {
		System.out.print("JavaProject_2018/2_");
		String sql;
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
			java.sql.Statement st = null;
			sql="create database PJ_PC;";
			st=con.createStatement();
			st.execute(sql);
			sql="use PJ_PC;";
			st.execute(sql);
			sql="create table PC (" + 
					"PC_NUMBER INT UNSIGNED ," + 
					"PC_STATUS INT UNSIGNED ," + 
					"PC_TEMP INT UNSIGNED" + 
					");";
			st=con.createStatement();
			st.execute(sql);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}