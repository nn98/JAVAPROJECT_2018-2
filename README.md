# JAVAPROJECT_2018-2
18년도 2학기 자바프로젝트  
<https://docs.google.com/document/d/1RZZauYzxfdyCoadlsPdHkOPiX5C98YlqXfCFtMt5Xcc/edit?usp=sharing>
### --------------
#### java part
#### 11/10 2:03 개별 데스크탑용 클라이언트 프로토타입 코드 완성.
#### 11/10 19:22 테스트용 노트북에 패치 후 실행-정상적으로 작동.
### --------------
#### mysql part
#### 11/8 1:02 JDBC 데이터베이스 접근 오류-java.sql.SQLNonTransientConnectionException: Public Key Retrieval is not allowed.
#### URL String에 &allowPublicKeyRetrieval=true 추가-해결.
#### 11/8 3:11 JDBC mysql 연동 프로젝트 업로드-단순 연결+데이터베이스 목록 조회.  
#### 11/8 23:10 클라이언트 실행 시 정상적으로 INSERT실행, UPDATE 실행실패. 추가 작업 통해 기본값 설정 후 UPDATE 실행 가능하도록 변경--
#### 11/9 20:21 원격 접속을 위한 사용자 추가와 권한 부여 시도-실패. -- 버전 업그레이드로 인한 구문 오류? //https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D_%ED%97%88%EC%9A%A9
#### 11/10 19:22 원격 접속을 위한 IP 대역 허용, 사용자 추가, 전체 IP 개방 모두 실패. 클라이언트 코드는 정상적으로 작동.
#### 11/11 0:59 DBTest+PingTest 조합, getLocalHostLANAddress 활용한 개별 데스크탑용 클라이언트 프로토타입 테스트.
#### 11/12 13:00~ mysql 외부 사용자,IP 추가 시도. password() 함수에서 지속적 오류 발생. 
####  ~ 14:52 홍은지 교수님꼐 질문- 해결 실패. ---차후 문성현 교수님꼐 질문
####  ~ 15:28 password 배제 후 전체 IP 개방형 사용자 추가 완료. ---방화벽 해지, my.ini_bind-address 제거 필요.
참고 __ http://dogcowking.tistory.com/154 , https://idchowto.com/?p=11068 ,  
https://zetawiki.com/wiki/MySQL_%EC%9B%90%EA%B2%A9_%EC%A0%91%EC%86%8D_%ED%97%88%EC%9A%A9  검색어 : mysql 외부 접속 허용
### --------------
#### android studio part
