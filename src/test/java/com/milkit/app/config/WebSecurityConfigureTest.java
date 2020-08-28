package com.milkit.app.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.milkit.app.common.exception.handler.ApiResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class WebSecurityConfigureTest {

    private PasswordEncoder passwordEncoder;
    
 
    @Test
    public void passwordEncoder_TEST() {
//    	passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	passwordEncoder = new StandardPasswordEncoder();
//    	passwordEncoder = new BCryptPasswordEncoder();
    	
        String password = "test";
 
        String encPassword = passwordEncoder.encode(password);
 
        log.debug(encPassword);
        
        assertThat(passwordEncoder.matches(password, encPassword)).isTrue();
    }
}
