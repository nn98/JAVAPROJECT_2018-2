import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class SepPingTest {

	public static void main(String[] args) {
		String sql=null,mIP=null;
		InetAddress targetIp=null;
		Connection con = null;
		java.sql.Statement st = null;
		ResultSet rs = null;
		try {
			mIP=getLocalHostLANAddress();
			targetIp = InetAddress.getByName(mIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://172.16.4.206:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
			
			int PC_STATUS=-1;
			int PC_NUMBER=Character.getNumericValue(mIP.charAt(mIP.length()-1));
			String addressName = "PC-"+Integer.toString(PC_NUMBER);
			st = con.createStatement();
			st.executeQuery("use project;");
			System.out.println(mIP+" "+mIP.length()+" "+mIP.charAt(mIP.length()-1)+" "+PC_NUMBER);
			while (true) {
				try {
					Thread.sleep(3000);
					boolean value = targetIp.isReachable(5000);
					if (value) {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>onLinePC");
						PC_STATUS=3;
					} else {
						System.out.println(getLocalHostLANAddress() + "-" + addressName
								+ " =>offLinePC");
						PC_STATUS=2;
					}
					sql = "update pc set pc_status=(?) where pc_number=(?)";    //insert시 인자값을 넣기 위해 사용
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, PC_STATUS);
					pst.setInt(2, PC_NUMBER);
					pst.execute();
					pst.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		catch (SQLException e1) {
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