# JAVAPROJECT_2018-2
18년도 2학기 자바프로젝트  
<https://docs.google.com/document/d/1RZZauYzxfdyCoadlsPdHkOPiX5C98YlqXfCFtMt5Xcc/edit?usp=sharing>
### --------------
#### java part
#### 11/10 2:03 개별 데스크탑용 클라이언트 프로토타입 코드 완성.
#### 11/10 19:22 테스트용 노트북에 패치 후 실행-정상적으로 작동.
#### 11/12 14:11 데이터베이스 update 구문 추가 성공. ---Insert-Update 구분or적절한 조합 통한 코드 수정,catch 수정 필요.
####  ~ 18:00 ~ 공유된 데이터베이스 사용자로 접속하기 위한 getConnection 수정, 수정된 getConnection 기반으로 데이터베이스 조회.
##### - 위의 코드로 기존의 Create,Drop,Test,PingTest,Main 전체 수정_ 성공. 깃허브에 업로드__
### --------------


#### mysql part
###### login--
###### create database PJ_PC;
###### use PJ_PC;
###### create table PC (
###### 		PC_NUMBER INT UNSIGNED ,
###### 		PC_STATUS INT UNSIGNED ,
###### 		PC_TEMP INT UNSIGNED
###### );
#### 11/8 1:02 JDBC 데이터베이스 접근 오류-java.sql.SQLNonTransientConnectionException: Public Key Retrieval is not allowed.
#### URL String에 &allowPublicKeyRetrieval=true 추가-해결.
#### 11/8 3:11 JDBC mysql 연동 프로젝트 업로드-단순 연결+데이터베이스 목록 조회.  
#### 11/8 23:10 클라이언트 실행_ INSERT실행성공, UPDATE 실행실패. ---기본값 설정 후 UPDATE 실행 가능하도록 변경--
#### 11/9 20:21 원격 접속을 위한 사용자 추가와 권한 부여 시도-실패. -- 버전 업그레이드로 인한 구문 오류?
//https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D_%ED%97%88%EC%9A%A9
#### 11/10 19:22 원격 접속을 위한 IP 대역 허용, 사용자 추가, 전체 IP 개방 모두 실패. 클라이언트 코드는 정상적으로 작동.
#### 11/11 0:59 DBTest+PingTest 조합, getLocalHostLANAddress 활용한 개별 데스크탑용 클라이언트 프로토타입 테스트.
#### 11/12 13:00~ mysql 외부 사용자,IP 추가 시도. password() 함수에서 지속적 오류 발생. 
####  ~ 14:11 클라이언트 update문 수정 성공- 정상 실행. Insert문 반복될 경우 Exception ---catch 수정 필요.
####  ~ 14:52 홍은지 교수님께 질문- 해결 실패. ---차후 문성현 교수님께 질문
####  ~ 15:28 password 배제 후 전체 IP 개방형 사용자 추가 완료. ---방화벽 해지, my.ini_bind-address 제거 필요.
참고 __ http://dogcowking.tistory.com/154 , https://idchowto.com/?p=11068 ,  
https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D_%ED%97%88%EC%9A%A9
##### 검색어 : mysql 외부 접속 허용
####  ~ 18:00 ~ ---Password 설정 Identified by로 수행.--- my.ini 수정 완료, 방화벽 해지. Mysql PC간 데이터베이스 접근 테스트.
참고 __ http://bblog.tistory.com/316 (password) , https://serverfault.com/questions/546281/mysql-bind-address-windows-error-1067-my-ini (bind-address) , http://savour75.tistory.com/21 (방화벽 해제)
##### - 테스트용 서버 PC에 외부 유저 설정.(notting@172...) 명령 프롬프트 상에서 액세스 시도-성공(SQL 서버 디렉토리에서 cmd 실행.)
참고 __ http://myblog.opendocs.co.kr/archives/1591 , https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D , http://godblessyk.tistory.com/entry/MySQL-%EC%9B%90%EA%B2%A9%EC%A0%91%EC%86%8D%ED%97%88%EC%9A%A9%EA%B3%BC-%EC%9B%90%EA%B2%A9%EC%A0%91%EC%86%8D%ED%99%95%EC%9D%B8 
##### - 성공한 유저 기반으로 JAVA PingTest 예시코드 수정- 
참고 __ https://blog.outsider.ne.kr/6
getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true","root","********");
->getConnection("jdbc:mysql://172.30.4.70:3306/?serverTimezone=UTC&useSSL=false &allowPublicKeyRetrieval=true","notting","*********");
##### - 성공. 깃허브에 업로드___
#### 11/19 12:10 데이터베이스 서버용으로 재설치_ 동일 네트워크 상 데이터베이스 연결 확인. Node 터미널에서 데이터 수신 확인
#### ~18:49 노드상 전송받은 데이터 필요한 형태로 재구축 시도 ---간헐적으로 발생하는 노드 기본설정 에러 확인 해결 필요.

### --------------
#### Node.js part
#### 11/19 19:04 node.js이용 데이터베이스 특정값 터미널 리턴 성공
#### 11/19 19:05~ localhost 데이터 베이스 값 출력 
###
### --------------
#### android studio part
