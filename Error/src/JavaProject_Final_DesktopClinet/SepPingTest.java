package JavaProject_Final_DesktopClinet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class SepPingTest {	//개별화, 타겟 데스크탑에 개별적으로 설치 예정

    public static void main(String[] args) throws UnknownHostException {
        String ip=getLocalHostLANAddress();	//현재 컴퓨터 IP 불러오기
        int PC_NUMBER=Integer.parseInt(ip.substring(ip.length()-2, ip.length()))-10;	//PC_NUMBER 추출(6202 기준)
        int PC_STATUS=-1;					//PC_STATUS 기본값 정의
        String addressName = "PC_"+PC_NUMBER;	//출력용 PC_NUMBER. 이후 주석처리
        try {
        	String sql;							//sql 초기화
            Connection con = null;				//sql 커넥션 초기화
            con = DriverManager.getConnection("jdbc:mysql://58.121.52.142:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true",
					"Project", "testing00");	//sql 커넥션 정의. 서버 IP 수정
            java.sql.Statement st = null;		//sql 스테이트먼트 초기화
            st = con.createStatement();			//sql 스테이트먼트 정의
            st.executeQuery("use pj_pc;");		//프로젝트용 데이터베이스 사용
            while (true) {
                try {
                    Thread.sleep(3000);			//스레드 대기시간
                    InetAddress targetIp = InetAddress.getByName(getLocalHostLANAddress());
                    boolean value = targetIp.isReachable(5000);		//핑 테스트
                    if (value) {				//온라인
                        System.out.println(getLocalHostLANAddress() + "-" + addressName
                                + " =>onLinePC");	//이후 주석처리
                        PC_STATUS=8;			//Status 8 - ON상태.
                    } else {					//오프라인
                        System.out.println(getLocalHostLANAddress() + "-" + addressName
                                + " =>offLinePC");	//이후 주석처리
                        PC_STATUS=3;			//Status 3 - OFF상태.

                    }
                    sql = "update pc set pc_status=? where pc_number=?";    //데이터베이스 업데이트
                    PreparedStatement pst = con.prepareStatement(sql);		//실행 대기 스테이트먼트
                    pst.setInt(1, PC_STATUS);	//Status 설정
                    pst.setInt(2, PC_NUMBER);	//Number 설정
                    pst.execute();				//스테이트먼트 실행
                    pst.close();				//스테이트먼트 종료. 무한반복이라 삭제 예정
                }
                catch (Exception e) {
                	System.out.println("Error!-Inner");	//내부 스레드 실행중 오류
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        	System.out.println("Error!-Outter");		//외부 sql 커넥트중 오류
            e.printStackTrace();
        }
    }

    private static String getLocalHostLANAddress() throws UnknownHostException {
        InetAddress local = null;		//현재 컴퓨터 IP 받아오는 함수
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = local.getHostAddress();
        return ip;
    }
}