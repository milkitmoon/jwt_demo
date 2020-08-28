package com.milkit.app.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.milkit.app.config.jwt.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppCommon {
	
	private AppCommon() {}
	
    private static class SingleTonHolder {
        private static final AppCommon instance = new AppCommon();
    }
     
    public static AppCommon getInstance() {
        return SingleTonHolder.instance;
    }
	
    public final String JWT_SECRETKEY = "jwt_demo";
    public final long JWT_EXPIRATION_TIME = 864000000;
    public final String JWT_TOKEN_PREFIX = "Bearer ";
    public final String JWT_HEADER_STRING = "Authorization";
	
}
