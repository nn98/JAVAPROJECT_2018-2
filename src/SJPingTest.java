import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SJPingTest {

	public static void main(String[] args) throws UnknownHostException {
		String sql;
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"root", "q192837q");
			java.sql.Statement st = null;
			ResultSet rs = null;
			String basicPc="pc-";
			String addressName=getLocalHostLANAddress();
			System.out.println(addressName);
			String address[] = addressName.split(".");
			System.out.println(Arrays.toString(address));
			st = con.createStatement();
			st.executeQuery("use project_pc;");
			int PC_NUMBER=43;
			int PC_STATUS=-1;
			while (PC_NUMBER<43) {
				try {
					Thread.sleep(3000);
					InetAddress targetIp = InetAddress.getByName(getLocalHostLANAddress());
					boolean value = targetIp.isReachable(5000);
					if (value) {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>onLinePC");
						PC_STATUS=1;
					} else {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>offLinePC");
						PC_STATUS=0;

					}
					//insert시 인자값을 넣기 위해 사용  
					sql = "insert into pc values (?,?,?)";//insert시 데이터 존재하면 오류
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, PC_NUMBER);
					pst.setInt(2, PC_STATUS);	//
					pst.setInt(3, 6202);
					pst.execute();
					pst.close();
					PC_NUMBER++;
					System.out.println(PC_NUMBER);
				}
				catch (Exception e) {	//전체오류 통틀어 처리. 구분 필요
					e.printStackTrace();
					System.out.println("씨발");
				}
			}
			PC_NUMBER=1;
			while (true) {
				try {
					Thread.sleep(3000);
					InetAddress targetIp = InetAddress.getByName(getLocalHostLANAddress());
					boolean value = targetIp.isReachable(5000);
					if (value) {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>onLinePC");
						PC_STATUS=5;
					} else {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>offLinePC");
						PC_STATUS=0;

					}
					sql = "update pc"+" set pc_status = (?)"+"where pc_id = (?)";    //insert시
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, PC_STATUS);
					pst.setInt(2, PC_NUMBER);
					pst.execute();
					pst.close();
					PC_NUMBER++;
					if(PC_NUMBER>42) PC_NUMBER=1;
					System.out.println(PC_NUMBER);
				}
				catch (Exception e) {	//전체오류 통틀어 처리. 구분 필요
					System.out.println("씨발");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}


	private static String getLocalHostLANAddress() throws UnknownHostException {
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = local.getHostAddress();
		return ip;
	}
}