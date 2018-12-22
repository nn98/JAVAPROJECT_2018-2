
package JavaProject_Final_DesktopClinet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class OperationTest {
	public static void main(String[] args) {
		System.out.print("JavaProject_2018/2_");
		String sql;
		int t=-1;
		Scanner sc=new Scanner(System.in);
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
			java.sql.Statement st = null;
			ResultSet rs = null;
			PreparedStatement pst;
			st = con.createStatement();
			st.executeQuery("use pj_pc;");
			while(t!=0) {
				System.out.print("\n\ninput Operation _(0- terminate 1- select 2- insert 3- delect)");
				t=sc.nextInt();
				switch(t) {
				case 1:
					sql="select * from pc";
					rs=st.executeQuery(sql);
					while(rs.next()) {
						String n=rs.getString("pc_number");
						String s=rs.getString("pc_status");
						String l=rs.getString("pc_temp");
						System.out.print("\n**	Number : "+n);
						System.out.print("	Status : "+s);
						System.out.print("	Temp : "+l);
					}
					break;
				case 2:
					int ex=0;
					System.out.print("select type _(1-single insert 2-multy insert)");
					int target=sc.nextInt();
					switch(target) {
					case 1:
						System.out.print("insert into pc values (?,?,?)");
						sql = "insert into pc values (?,?,?)";   
						pst = con.prepareStatement(sql);
						System.out.print("\nvalue 1=");
						ex=sc.nextInt();
						pst.setInt(1, ex);
						System.out.print("\nvalue 2=");
						ex=sc.nextInt();
						pst.setInt(2, ex);
						System.out.print("\nvalue 3=");
						ex=sc.nextInt();
						pst.setInt(3, ex);
						pst.execute();
						break;
					case 2:
						System.out.print("select repeat count:");
						int count=sc.nextInt();
						/*
						System.out.print("select increse column. 1?");
						int first=sc.nextInt();
						System.out.print("select increse column. 2?");
						int second=sc.nextInt();
						System.out.print("select increse column. 3?");
						int third=sc.nextInt();
						 */
						System.out.print("insert into pc values (?,?,?)");
						sql = "insert into pc values (?,?,?)";  
						pst = con.prepareStatement(sql);
						System.out.print("\nvalue 1=");
						int first=sc.nextInt();
						pst.setInt(1, first);
						System.out.print("\nvalue 2=");
						int second=sc.nextInt();
						pst.setInt(2, second);
						System.out.print("\nvalue 3=");
						int third=sc.nextInt();
						pst.setInt(3, third);
						pst.execute();
						for(int i=1;i<count;i++) {
							pst.setInt(1, first+i);
							pst.execute();
						}
						break;
					}
					break;
				case 3:
					ex=0;
					System.out.print("select target _(1-pc_number 2-pc_status 3-pc_temp)");
					target=sc.nextInt();
					switch(target) {
					case 1:
						System.out.print("delete from pc where pc_number=(?)");
						sql = "delete from pc where pc_number=(?)";    
						ex=sc.nextInt();
						pst = con.prepareStatement(sql);
						pst.setInt(1, ex);
						pst.execute();
						break;
					case 2:
						System.out.print("delete from pc where pc_status=(?)");
						sql = "delete from pc where pc_status=(?)";  
						ex=sc.nextInt();
						pst = con.prepareStatement(sql);
						pst.setInt(1, ex);
						pst.execute();
						break;
					case 3:
						System.out.print("delete from pc where pc_temp=(?)");
						sql = "delete from pc where pc_temp=(?)";    
						ex=sc.nextInt();
						pst = con.prepareStatement(sql);
						pst.setInt(1, ex);
						pst.execute();
						break;
					}
					System.out.println("Operation success.");
					break;
				default:

				}
			}
		}
		catch(Exception e) {
			System.out.println("Operation fail.");
			e.printStackTrace();
		}

	}
}