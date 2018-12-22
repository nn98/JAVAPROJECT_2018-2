package JavaProject_Final_DesktopClinet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class SepPingTest {	//����ȭ, Ÿ�� ����ũž�� ���������� ��ġ ����

    public static void main(String[] args) throws UnknownHostException {
        String ip=getLocalHostLANAddress();	//���� ��ǻ�� IP �ҷ�����
        int PC_NUMBER=Integer.parseInt(ip.substring(ip.length()-2, ip.length()))-10;	//PC_NUMBER ����(6202 ����)
        int PC_STATUS=-1;					//PC_STATUS �⺻�� ����
        String addressName = "PC_"+PC_NUMBER;	//��¿� PC_NUMBER. ���� �ּ�ó��
        try {
        	String sql;							//sql �ʱ�ȭ
            Connection con = null;				//sql Ŀ�ؼ� �ʱ�ȭ
            con = DriverManager.getConnection("jdbc:mysql://58.121.52.142:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");	//sql Ŀ�ؼ� ����. ���� IP ����
            java.sql.Statement st = null;		//sql ������Ʈ��Ʈ �ʱ�ȭ
            st = con.createStatement();			//sql ������Ʈ��Ʈ ����
            st.executeQuery("use pj_pc;");		//������Ʈ�� �����ͺ��̽� ���
            while (true) {
                try {
                    Thread.sleep(3000);			//������ ���ð�
                    InetAddress targetIp = InetAddress.getByName(getLocalHostLANAddress());
                    boolean value = targetIp.isReachable(5000);		//�� �׽�Ʈ
                    if (value) {				//�¶���
                        System.out.println(getLocalHostLANAddress() + "-" + addressName
                                + " =>onLinePC");	//���� �ּ�ó��
                        PC_STATUS=8;			//Status 8 - ON����.
                    } else {					//��������
                        System.out.println(getLocalHostLANAddress() + "-" + addressName
                                + " =>offLinePC");	//���� �ּ�ó��
                        PC_STATUS=3;			//Status 3 - OFF����.

                    }
                    sql = "update pc set pc_status=? where pc_number=?";    //�����ͺ��̽� ������Ʈ
                    PreparedStatement pst = con.prepareStatement(sql);		//���� ��� ������Ʈ��Ʈ
                    pst.setInt(1, PC_STATUS);	//Status ����
                    pst.setInt(2, PC_NUMBER);	//Number ����
                    pst.execute();				//������Ʈ��Ʈ ����
                    pst.close();				//������Ʈ��Ʈ ����. ���ѹݺ��̶� ���� ����
                }
                catch (Exception e) {
                	System.out.println("Error!-Inner");	//���� ������ ������ ����
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        	System.out.println("Error!-Outter");		//�ܺ� sql Ŀ��Ʈ�� ����
            e.printStackTrace();
        }
    }

    private static String getLocalHostLANAddress() throws UnknownHostException {
        InetAddress local = null;		//���� ��ǻ�� IP �޾ƿ��� �Լ�
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = local.getHostAddress();
        return ip;
    }
}