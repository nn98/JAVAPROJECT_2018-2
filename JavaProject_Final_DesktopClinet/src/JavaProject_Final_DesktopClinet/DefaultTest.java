package JavaProject_Final_DesktopClinet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DefaultTest {
	public static void main(String[] args) {
		String[][] testing = new String[42][2];	// ���̺� �� ����� �迭
		int i=0;								// ���̺� ���� �迭�� ���Ǻ���
		String sql;				
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:1206/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			System.out.println("��ü �����ͺ��̽� Ȯ��_");			//
			rs=st.executeQuery("show databases;");
			while(rs.next()) {
				String str=rs.getNString(1);
				System.out.println(str);
			}
			System.out.println("�׽�Ʈ�� �����ͺ��̽� �÷� �� Ȯ��_");	//
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
		System.out.println("�׽�Ʈ�� �����ͺ��̽� �÷� �� �迭ȭ_");	//
		for(int a=0;a<testing.length;a++) {
			for(int b=0;b<testing[a].length;b++) {
				System.out.print(testing[a][b]+"	");
			}
			System.out.println();
		}
	}
}