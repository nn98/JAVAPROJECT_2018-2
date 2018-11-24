import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class PingTest {

    public static void main(String[] args) {
        String sql;
        try {
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://172.16.4.206:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");
            java.sql.Statement st = null;
            ResultSet rs = null;
            String basicPc="pc-";
            int PC_NUMBER=1;
            int PC_STATUS=-1;
            String addressName = "mycomputer";
            st = con.createStatement();
            st.executeQuery("use project;");
            while (true) {
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
        } catch (SQLException e1) {
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