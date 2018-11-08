import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) {
		String sql;
		try {
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"root", "q192837q");
			java.sql.Statement st = null;
			ResultSet rs = null;
			String basicIp="192.168.61.";
			String basicPc="pc-";
			int indivisualIp=111;
			String t=basicIp+Integer.toString(indivisualIp);
			String[] addressTmp=new String[42];
			String[] addressName=new String[42];
			int PC_NUMBER=1;
			int PC_STATUS=-1;
			for(int i=0;i<42;i++) {
				addressTmp[i]=basicIp+Integer.toString(indivisualIp);
				addressName[i]=basicPc+Integer.toString(i+1);
				indivisualIp++;
			}
			st = con.createStatement();
			st.executeQuery("use 6202_pc;");
			while (true) {
				for(int i=0;i<addressTmp.length;i++) {
					try {
						Thread.sleep(1000);
						InetAddress targetIp = InetAddress.getByName(addressTmp[i]);
						boolean value=targetIp.isReachable(1000);
						if(value) {
							System.out.println(addressTmp[i]+"-"+addressName[i]+"=>onLinePC");
							PC_STATUS=1;
						} else {
							System.out.println(addressTmp[i]+"-"+addressName[i]+"=>offLinePC");
							PC_STATUS=8;
						}
						sql = "insert into pc"+String.format("%02d", PC_NUMBER)+" values(?,?)";    //insert시 인자값을 넣기 위해 사용
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setInt(1, PC_NUMBER);
						pst.setInt(2, PC_STATUS);
						pst.execute();
						pst.close();
						PC_NUMBER++;
					}
					catch (Exception e) {
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}