package com.milkit.app.config.jwt.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkit.app.common.AppCommon;
import com.milkit.app.common.exception.AttemptAuthenticationException;
import com.milkit.app.config.jwt.JwtTokenProvider;
import com.milkit.app.domain.userinfo.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenProvider jwtTokenProvider;
	
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtTokenProvider = new JwtTokenProvider();
    }
    
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


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    	UserInfo principal = (UserInfo) authResult.getPrincipal();
    	String token = jwtTokenProvider.createToken(principal);
        response.addHeader(AppCommon.getInstance().JWT_HEADER_STRING, AppCommon.getInstance().JWT_TOKEN_PREFIX + token);
    }
}
