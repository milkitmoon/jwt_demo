# 1. 개요
- 간단한 JWT 기반의 토큰방식 인증을 구현하였습니다.
- 사용자는 아이디와 비밀번호를 통해 로그인을 요청하고 JWT 토큰을 전달받습니다.
- 사용자는 JWT 토큰을 HTTP HEADER에 넣은 후 REST API를 호출합니다.
- 서버는 HEADER에 있는 JWT 토큰을 Parsing하여 유효한 토큰인지 확인 후 인증을 허용해 줍니다.
- 사용자의 권한에 따라 접근 가능한 URL이 제한됩니다.


# 2. 기술명세
- 언어 : Java 1.8
- IDE : STS 4
- 프레임워크 : spring boot 2.3.1
- 의존성 & 빌드 관리 : gradle
- JWT 인증 : spring security, jjwt
- Persistence : JPA (Hibernate) 
- Database : H2 (in memory)
- OAS : Swagger

> Swagger API명세 페이지 보기
- 어플리케이션 기동 후 아래와 같이 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 접속하여 API페이지를 조회할 수 있습니다.
<img src="https://user-images.githubusercontent.com/61044774/91526280-35ea1980-e93e-11ea-9dc8-ed7792f53a37.jpg" width="90%"></img>


> H2 database 웹콘솔 보기
- H2 웹console 접속경로는 다음과 같습니다. [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) 
<img src="https://user-images.githubusercontent.com/61044774/85590819-b0b56080-b67f-11ea-8415-3eb50f5b82b8.jpg" width="90%"></img>

- Driver Class : org.h2.Driver
- JDBC URL : jdbc:h2:mem:testdb
- User Name : sa
- Password : [없음]


# 3. 빌드 및 실행

> Tips 
- 만약 빌드가 제대로 진행되지 않거나 오류가 발생 시 아래와 같이 STS에서 'Refresh Gradle Project' 를 클릭해 주세요
<img src="https://user-images.githubusercontent.com/61044774/85550736-34f5ec80-b65c-11ea-865b-981c6b72f2b9.jpg" width="90%"></img>

---

> Tips 
- 만약 lombok 관련 오류가 발생하면 아래의 url을 참조해 주세요
[https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts](https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts)
[https://countryxide.tistory.com/16](https://countryxide.tistory.com/16)

---

## build 하기 (war 파일 생성)

- 아래 그림과 같이 'Gradle Tasks' 메뉴에서 'bootWar' 를 오른쪽 클릭하여 'Run Gradle Tasks' 를 실행합니다.
<img src="https://user-images.githubusercontent.com/61044774/85556604-f5320380-b661-11ea-8020-04290b8762d4.jpg" width="90%"></img>

- [프로젝트경로]/build/lib 경로에 build 된 war 파일이 생성됩니다.
<img src="https://user-images.githubusercontent.com/61044774/85557150-7d180d80-b662-11ea-9fd8-2bd1bbc23df3.jpg" width="90%"></img>


## 실행 하기

> 소스 main Application 실행하기 
- com.milkit.app.DemoApplication 을 STS에서 run하여 바로 실행할 수 있습니다.
 <img src="https://user-images.githubusercontent.com/61044774/91526490-b1e46180-e93e-11ea-9c03-6385d281d944.jpg" width="90%"></img>


# 4. 처리과정

## 인증 요청
- http://localhost:8080/login URL로 POST로 인증정보를 전달합니다.
<img src="https://user-images.githubusercontent.com/61044774/91527564-f670fc80-e940-11ea-85c7-142ad36b8841.jpg" width="90%"></img>
  * 사용자 계정은 admin / test 혹은 test / test로 지정할 수 있습니다. (admin은 ROLE_ADMIN 권한, test는 ROLE_MEMBER 권한)
  * 사용자 계정은 POST Body에 다음과 같은 형식의 json 값을 설정한다
  ```javascript
  {
	"username" : "admin",
	"password" : "test"
  }
  ```
  * 사용자가 인증되었다면 서버는 Response HEADER의 Authorization 의 값으로 JWT Token을 전달합니다. 

---

## API 호출
- http://localhost:8080/api/userinfo URL을 GET으로 호출하여 사용자 정보를 요청합니다.
<img src="https://user-images.githubusercontent.com/61044774/91528741-025dbe00-e943-11ea-81af-2e4ca5a1d261.jpg" width="90%"></img>
  * 사용자는 인증요청에서 응답받은 JWT Token을 Authorization Header에 입력합니다.
  * 서버는 API Request의 Header의 JWT Token을 확인하고 권한확인 및 접근제어를 수행합니다.


# 5. 구현기능
> 구현기능
- 공지 등록/수정/삭제기능
- 공지목록 조회 기능 (제목, 작성일, 작성자, 최종수정일, 내용)
- 페이징 기능 (10개 마다)
- 여러개의 첨부파일 등록 기능

**파일업로드 후 이미지가 안보일 수 있습니다. 그럴경우 아래와 같이 Project를 refresh 해주세요.**

<img src="https://user-images.githubusercontent.com/61044774/85596326-95008900-b684-11ea-9d44-ff2fd1bc166e.jpg" width="90%"></img>

