package com.milkit.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.milkit.app.config.jwt.filter.JwtAuthenticationFilter;
import com.milkit.app.config.jwt.filter.JwtAuthenticationProvider;
import com.milkit.app.config.jwt.filter.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {


	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/h2-console/**",
        		
        		"/v2/api-docs", 
        		"/configuration/ui",
                "/swagger-resources/**", 
                "/swagger-ui.html", 
                "/webjars/**",
                "/swagger/**"
        	);
    }
	

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
//        return new StandardPasswordEncoder();	//SHA-256
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

}