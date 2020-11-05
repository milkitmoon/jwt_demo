# 1. 개요
- 간단한 JWT 기반의 토큰방식 인증을 구현하였습니다.
- 사용자는 아이디와 비밀번호를 통해 로그인을 요청하고 JWT 토큰을 전달받습니다.
- 사용자는 JWT 토큰을 HTTP HEADER에 넣은 후 REST API를 호출합니다.
- 서버는 HEADER에 있는 JWT 토큰을 Parsing하여 유효한 토큰인지 확인 후 접근제어를 수행합니다.
- 사용자의 권한에 따라 접근 가능한 URL이 제한됩니다. (ROLE_ADMIN, ROLE_MEMBER)


# 2. 기술명세
- 언어 : Java 1.8
- IDE : STS 4
- 프레임워크 : spring boot 2.3.1
- 의존성 & 빌드 관리 : gradle
- JWT 인증 : spring security, jjwt
- Persistence : JPA (Hibernate) 
- Database : H2 (in memory)
- OAS : swagger

> swagger API명세 페이지 보기
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

---

> Tips 
- **만약 lombok 관련 오류가 발생하면 아래의 url을 참조해 주세요**
[https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts](https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts)
[https://countryxide.tistory.com/16](https://countryxide.tistory.com/16)
[https://planbsw.tistory.com/109](https://planbsw.tistory.com/109)

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
<img src="https://user-images.githubusercontent.com/61044774/93299272-c033e800-f82f-11ea-852d-9da348dfdf30.jpg" width="90%"></img>
  * 사용자 계정은 admin / test 혹은 test / test로 지정할 수 있습니다. (admin은 ROLE_ADMIN 권한, test는 ROLE_MEMBER 권한)
  * 사용자 계정은 POST Body에 다음과 같은 형식의 json 값을 설정합니다.
  ```javascript
  {
	"username" : "admin",
	"password" : "test"
  }
  ```
  * 사용자가 인증되었다면 서버는 Response body에 JWT Token 정보를 전달합니다. 
  ```javascript
  {
    "code": "0",
    "message": "성공했습니다",
    "value": {
      "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTk0iOiLqtIDrpqzsnpAiLCJhdXRoUm9sZSI6IlJPTEVfQURNSU4iLCJuYW1lIjoiYWRtaW4iLCJleHAiOjE2MDAyMzQxMjgsImlhdCI6MTYwMDIzMjMyOH0.hYTzcG5nDhdVn4OVbrrH7ybSLwBxq1Fm2O9A60uk8Zw",
      "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTk0iOiLqtIDrpqzsnpAiLCJhdXRoUm9sZSI6IlJPTEVfQURNSU4iLCJuYW1lIjoiYWRtaW4iLCJleHAiOjE2MDE0NDE5MzAsImlhdCI6MTYwMDIzMjMzMH0.MZLH17FUuUqYzlZDQ2AZDcRnSvxT2QJJeLHhiwtJFDo",
      "tokenType": "bearer"
    }
  }
  ```
---

## 토큰 재발급
- http://localhost:8080/refresh URL로 토큰정보 재발급을 요청합니다.
<img src="https://user-images.githubusercontent.com/61044774/93299989-f6be3280-f830-11ea-9873-0cd627d18072.jpg" width="90%"></img>
  * 사용자는 인증요청에서 응답받은 JWT refresh Token 값을 Authorization Header에 입력합니다.
  * 사용자의 토큰이 유효한 것인지 확인되었다면 서버는 Response body에 JWT Token 정보를 전달합니다. 
  ```javascript
  {
    "code": "0",
    "message": "성공했습니다",
    "value": {
      "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTk0iOiLqtIDrpqzsnpAiLCJhdXRoUm9sZSI6IlJPTEVfQURNSU4iLCJuYW1lIjoiYWRtaW4iLCJleHAiOjE2MDAyMzkxODcsImlhdCI6MTYwMDIzNzM4N30.vp16ZPTySBEUJd3PxQd9ng3hnMBmOVoWrZksnXbw_5o",
      "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTk0iOiLqtIDrpqzsnpAiLCJhdXRoUm9sZSI6IlJPTEVfQURNSU4iLCJuYW1lIjoiYWRtaW4iLCJleHAiOjE2MDE0NDY5ODcsImlhdCI6MTYwMDIzNzM4N30.6XqQb6INp3IU-0IwHALG5lsIfC5PeUehekbQsKU2stE",
      "tokenType": "bearer"
    }
  }
  ```
---

## API 호출
- http://localhost:8080/api/userinfo URL을 GET으로 호출하여 사용자 정보를 요청합니다.
<img src="https://user-images.githubusercontent.com/61044774/91528741-025dbe00-e943-11ea-81af-2e4ca5a1d261.jpg" width="90%"></img>
  * 사용자는 인증요청에서 응답받은 JWT Token 값을 Authorization Header에 입력합니다.
  * 서버는 API Request Header의 JWT Token을 확인하고 권한확인 및 접근제어를 수행합니다.


# 5. Spring Security 처리 과정
- Spring Security config에서 인증(JwtAuthenticationFilter)과 접근제어(JwtAuthorizationFilter)에 대한 필터를 등록합니다.
  ```java
  
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        	.httpBasic().disable() 
	        .csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .antMatchers("/api/greeting").hasAnyRole("MEMBER", "ADMIN")
	        .antMatchers("/api/userinfo/**").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	        .addFilterBefore(new JwtAuthorizationFilter(authenticationManager()), BasicAuthenticationFilter.class)
	        .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
	        ;
        
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public JwtAuthenticationProvider authenticationProvider() {
        return new JwtAuthenticationProvider(passwordEncoder());
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
    }
    
  ```
- JwtAuthenticationFilter에서 UsernamePasswordAuthenticationToken을 생성하여 AuthenticaionManager에게 전달합니다.
  ```java
  
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	UsernamePasswordAuthenticationToken authenticationToken;
    	
		try {
			UserInfo credentials = new ObjectMapper().readValue(request.getInputStream(), UserInfo.class);
	        authenticationToken = new UsernamePasswordAuthenticationToken(
	                credentials.getUsername(),
	                credentials.getPassword(),
	                new ArrayList<>()
	        );
		} catch (IOException e) {
			e.printStackTrace();
			throw new AttemptAuthenticationException();
		}

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

  ``` 
- 인증을 위임받은 JwtAuthenticationProvider는 UserDetailsService를 통해 입력받은 아이디에 대한 사용자 정보를 DB에서 조회하여 인증을 수행합니다.
  ```java
  
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        
        String userID = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(userID);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
  ``` 
- JwtAuthenticationFilter는 전달받은 UsernameAuthenticationToken을 재정의된 successfulAuthentication 메서드로 전송하고, JWT 토큰을 생성하여 Response 의 헤더에 추가하여 반환합니다.
  ```java

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    	UserInfo principal = (UserInfo) authResult.getPrincipal();
    	String token = jwtTokenProvider.createToken(principal);
        response.addHeader(AppCommon.getInstance().JWT_HEADER_STRING, AppCommon.getInstance().JWT_TOKEN_PREFIX + token);
    }
    
  ``` 
- JwtAuthorizationFilter에서 사용자가 API url을 요청 시 Request Header의 JWT Token을 확인하고 권한확인 및 접근제어를 수행합니다.
  ```java
  
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AppCommon.getInstance().JWT_HEADER_STRING);

        if(header == null || !header.startsWith(AppCommon.getInstance().JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
    	Authentication authentication = null;
        String token = request.getHeader(AppCommon.getInstance().JWT_HEADER_STRING);
        if(token != null) {
        	Claims claims = jwtTokenProvider.getClaims(token.replace(AppCommon.getInstance().JWT_TOKEN_PREFIX, ""));
        	
            if(claims != null) {
            	authentication = new UsernamePasswordAuthenticationToken(claims.get("name"), null, UserInfo.getAuthorities((String)claims.get("authRole")));
            }
        }
        
        return authentication;
    }

  ``` 


