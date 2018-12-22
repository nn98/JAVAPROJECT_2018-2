# JAVAPROJECT_2018-2
18년도 2학기 자바프로젝트  
<https://docs.google.com/document/d/1RZZauYzxfdyCoadlsPdHkOPiX5C98YlqXfCFtMt5Xcc/edit?usp=sharing>


11/22 11:30 까지의 ReadMe.md 작성상황 공유. 이후 개별작성 후 Marge
* * *
* java part
  * 11/10 2:03 개별 데스크탑용 클라이언트 프로토타입 코드 완성.
  * 11/10 19:22 테스트용 노트북에 패치 후 실행-정상적으로 작동.
  * 11/12 14:11 데이터베이스 update 구문 추가 성공. ---Insert-Update 구분or적절한 조합 통한 코드 수정,catch 수정 필요.
  *  ~ 18:00 ~ 공유된 데이터베이스 사용자로 접속하기 위한 getConnection 수정, 수정된 getConnection 기반으로 데이터베이스 조회.
    * 위의 코드로 기존의 Create,Drop,Test,PingTest,Main 전체 수정_ 성공. 깃허브에 업로드__
  * 12/16 0:02 ~ 최종 프로젝트 구현용 데이터베이스 조작 프로젝트 생성- 리포지토리 Final_Testing 브랜치에 업로드.
  
    
* * *
###### login--
###### create database PJ_PC;
###### use PJ_PC;
###### create table PC (
###### 		PC_NUMBER INT UNSIGNED ,
###### 		PC_STATUS INT UNSIGNED ,
###### 		PC_TEMP INT UNSIGNED
###### );
- mysql part
  - 11/8 1:02 JDBC 데이터베이스 접근 오류-java.sql.SQLNonTransientConnectionException: Public Key Retrieval is not allowed.
  - URL String에 &allowPublicKeyRetrieval=true 추가-해결.
  - 11/8 3:11 JDBC mysql 연동 프로젝트 업로드-단순 연결+데이터베이스 목록 조회.  
  - 11/8 23:10 클라이언트 실행_ INSERT실행성공, UPDATE 실행실패. ---기본값 설정 후 UPDATE 실행 가능하도록 변경--
  - 11/9 20:21 원격 접속을 위한 사용자 추가와 권한 부여 시도-실패. -- 버전 업그레이드로 인한 구문 오류?
    - [데이터베이스 원격 접속(구버전)](https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D_%ED%97%88%EC%9A%A9)
  - 11/10 19:22 원격 접속을 위한 IP 대역 허용, 사용자 추가, 전체 IP 개방 모두 실패. 클라이언트 코드는 정상적으로 작동.
  - 11/11 0:59 DBTest+PingTest 조합, getLocalHostLANAddress 활용한 개별 데스크탑용 클라이언트 프로토타입 테스트.
  - 11/12 13:00~ mysql 외부 사용자,IP 추가 시도. password() 함수에서 지속적 오류 발생. 
    -  ~14:11 클라이언트 update문 수정 성공- 정상 실행. Insert문 반복될 경우 Exception ---catch 수정 필요.
    -  ~ 14:52 홍은지 교수님께 질문- 해결 실패. ---차후 문성현 교수님께 질문
    -  ~ 15:28 password 배제 후 전체 IP 개방형 사용자 추가 완료. ---방화벽 해지, my.ini_bind-address 제거 필요.
      - [전체 과정 간략 설명](https://idchowto.com/?p=11068) , [외부 IP 원격접속 허용](http://dogcowking.tistory.com/154)*검색어 : mysql 외부 접속 허용*
    -  ~ 18:00 ~ ---Password 설정 Identified by로 수행.--- my.ini 수정 완료, 방화벽 해지. Mysql PC간 데이터베이스 접근 테스트.
      - [유저 비밀번호 설정](http://bblog.tistory.com/316) , [my.ini의 bind-address 설정](https://serverfault.com/questions/546281/mysql-bind-address-windows-error-1067-my-ini) , [방화벽 해제](http://savour75.tistory.com/21)
    - 테스트용 서버 PC에 외부 유저 설정.(notting@172...) 명령 프롬프트 상에서 액세스 시도-성공(SQL 서버 디렉토리에서 cmd 실행.)
      - [최근버전 비밀번호 설정](http://myblog.opendocs.co.kr/archives/1591) , [구버전 원격 접속](https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D) , [최근버전 접속권한 설정, 원격 접속 방법](http://godblessyk.tistory.com/entry/MySQL-%EC%9B%90%EA%B2%A9%EC%A0%91%EC%86%8D%ED%97%88%EC%9A%A9%EA%B3%BC-%EC%9B%90%EA%B2%A9%EC%A0%91%EC%86%8D%ED%99%95%EC%9D%B8)
    - 성공한 유저 기반으로 JAVA PingTest 예시코드 수정- 
      - [jdbc 원격 접속 방법](https://blog.outsider.ne.kr/6)
      - getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true","root","********");
      - ->getConnection("jdbc:mysql://172.30.4.70:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true","notting","*********");
    - 성공. 깃허브에 업로드___
  - 11/19 12:10 서버용 데이터베이스 재설치, 동일 네트워크 한정 접근 가능 확인. Node 터미널에서 데이터 수신 성공
    - ~18:49 노드상 전송받은 데이터 필요한 형태로 재구축 시도 ---간헐적으로 발생하는 노드 기본설정 에러 확인 해결 필요.
  - 11/27 13:10 ~ getLocalHostLANAddress() 메소드를 활용한 개별 자바 클라이언트 테스트- 성공. ---LANschool 활용해 전체 데스크탑 테스트
  - 12/3 16:32 ~ 서버 노트북 교체, 재설정. 
    - 서버 포트 변경 필요? [mysql 포트 변경](https://skylit.tistory.com/253) 3306 포트 외부 네트워크 차단 가능성 존재.(문성현 교수님)
    - 18:22 ~ 재설정 데이터베이스로 연결 시도-성공.(3306 포트, 동일 네트워크) 안드로이드 클라이언트 구축 후 Node 연동 작업 필요.
  - 12/4 12:01 ~ 포트 변경(227) 후 연결 시도- 6202 데스크탑 연결 성공, Node 개발 노트북 연결 실패.
    - 14:38 ~ 포트 초기화와 재설정 시도 중 에러 발생. mysql 로그인 불가 ---재설치 필요..
  - 12/6 11:29 ~ mysql 서버 재설치 후 포트 1206으로 설정. 6202 데스크탑 연결 성공 [콘솔로 원격접속](http://blog.iotinfra.net/?p=1835)
    - 18:42 ~ 해당 리포지토리 master 브랜치에 새로운 Create 클래스 커밋 시도-실패, 파일 손상. ---재작성 
  - 12/10 14:28 ~ 1206 포트 서버에 프로젝트 테이블 생성- 외부 접속 테스트- 성공.
    - 17:12 ~ 박현렬 Node.js 통한 안드로이드 패킷 업로드 시도- 성공. 패킷 select 조건 추가와 업로드된 패킷 레이아웃에 적용 필요.
    - 18:51 ~ Node, Android 기반 테이블 수정, 수정된 테이블에 맞춰 기존 서버조작 클라이언트(Create, Drop, Update) 수정.
    - 18:59 ~ 수정된 서버 데이터베이스 조작에 불편함 존재. 조작용 클라이언트 작성 위해 JDBC 문법 재확인.
  - 12/12 17:10 ~ 박현렬 Node와 Android 연동 작업 수행- 성공. 테스트용 데이터베이스와 안드로이드 레이아웃, 액티비티 부분 수정.
  - 12/13 15:21 ~ 수정된 데이터베이스와 안드로이드 클라이언트 확인, 연동과 최종 시연 위한 구현, 연동 마무리 작업 돌입.
  - 12/16 00:02 ~ 최종 프로젝트 구현용 데이터베이스 조작 프로젝트 생성- 리포지토리 Final_Testing 브랜치에 업로드.-Final_Testing
  - 12/18 12:?? ~ 최종 프로젝트 구현용 데스크탑 클라이언트 PingTest 수정과 테스트 예정.
  - 12/21 ??:?? ~ 데이터베이스 연결(PingTest, SepPingTest) 클라이언트 업로드. 깃허브 업로드 에러.-Final_Testing
  - 12/22 ??:?? ~ 기본 데이터베이스 생성(FirstCreate) 클라이언트 업로드, 깃 업로드 브랜치 병합으로 에러 해결.-Final_Testing
* * *
- Node.js part
  - 11/19 19:04 node.js이용 데이터베이스 특정값 터미널 리턴 성공
  - 11/19 19:05~ localhost 데이터 베이스 값 출력 

* * *
- android studio part
 - 11/26 16:20~ 안드로이드 클라이언트 프로토타입 구현시도-레이아웃 전환과 인텐트 값 전달, 이미지 버튼. [Github Repository](https://github.com/nn98/SJ_Implements)
