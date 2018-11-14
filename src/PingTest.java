import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Arrays;

public class PingTest {

    public static void main(String[] args) throws UnknownHostException {
        String sql;
        try {
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
                    "root", "q192837q");
            java.sql.Statement st = null;
            ResultSet rs = null;
            String basicPc="pc-";
            int PC_NUMBER=1;
            int PC_STATUS=-1;
            String addressName=getLocalHostLANAddress();
            System.out.println(addressName);
            String address[] = addressName.split(".");
            System.out.println(Arrays.toString(address));
            
            st = con.createStatement();
            st.executeQuery("use 6202_pc;");
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
                sql = "insert into pc"+String.format("%02d", PC_NUMBER)+" values(?,?)";//insert시 데이터 존재하면 오류
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, PC_NUMBER);
                pst.setInt(2, PC_STATUS);	//
                pst.execute();
                pst.close();
                PC_NUMBER++;
                System.out.println(PC_NUMBER);
            }
            catch (Exception e) {	//전체오류 통틀어 처리. 구분 필요
            }
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
                    sql = "update pc"+String.format("%02d", PC_NUMBER)+" set PC_STATUS = ?";    //insert시
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, PC_STATUS);
                    pst.execute();
                    pst.close();
                    PC_NUMBER++;System.out.println(PC_NUMBER);
                }
                catch (Exception e) {	//전체오류 통틀어 처리. 구분 필요
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