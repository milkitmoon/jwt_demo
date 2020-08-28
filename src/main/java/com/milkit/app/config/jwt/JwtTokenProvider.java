package com.milkit.app.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import com.milkit.app.common.AppCommon;
import com.milkit.app.config.jwt.filter.JwtAuthenticationProvider;
import com.milkit.app.domain.userinfo.UserInfo;

@Slf4j
@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

	public String createToken(JwtDetails principal) {
		
        Date now = new Date();
        Date validity = new Date(now.getTime() + AppCommon.getInstance().JWT_EXPIRATION_TIME);
        
		return Jwts.builder().setSubject(principal.getSubject())
		//		  .setHeader(createHeader()) 
				  .setClaims(principal.getClaims())
		          .setIssuedAt(now)
		          .setExpiration(validity)
		          .signWith(SignatureAlgorithm.HS256, AppCommon.getInstance().JWT_SECRETKEY)
		          .compact();
	}
    
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }
    
    private Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(AppCommon.getInstance().JWT_SECRETKEY))
                .parseClaimsJws(token).getBody();
    }
    
    public Claims getClaims(String token) {
        try {
            Claims claims = parseClaims(token);

            log.debug("expireTime :" + claims.getExpiration());
            log.debug("userID :" + claims.get("name"));
            log.debug("userNM :" + claims.get("userNM"));
            log.debug("authRole :" + claims.get("authRole"));
            
            return claims;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return null;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return null;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return null;
        }
    }
}
