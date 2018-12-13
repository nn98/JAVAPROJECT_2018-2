package JavaProject_Final_DesktopClinet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DefaultTest {
	public static void main(String[] args) {
		String[][] testing = new String[42][2];	// 테이블 값 저장용 배열
		int i=0;								// 테이블 저장 배열용 조건변수
		String sql;				
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:1206/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			System.out.println("전체 데이터베이스 확인_");			//
			rs=st.executeQuery("show databases;");
			while(rs.next()) {
				String str=rs.getNString(1);
				System.out.println(str);
			}
			System.out.println("테스트용 데이터베이스 컬럼 값 확인_");	//
			st.executeQuery("use pj_pc;");
			sql="select pc_number,pc_status from pc;";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				String n=rs.getString("pc_number");
				String s=rs.getString("pc_status");
				System.out.print("\n**	Number : "+n);
				System.out.print("	Status : "+s);
				testing[i][0]=n;
				testing[i][1]=s;
				i++;
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("테스트용 데이터베이스 컬럼 값 배열화_");	//
		for(int a=0;a<testing.length;a++) {
			for(int b=0;b<testing[a].length;b++) {
				System.out.print(testing[a][b]+"	");
			}
			System.out.println();
		}
	}
}