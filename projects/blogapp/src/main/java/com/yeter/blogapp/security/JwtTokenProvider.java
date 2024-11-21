package com.yeter.blogapp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${blogapp.app.secret}")
    private String APP_SECRET; // key yaratdigimiz zaman istfde olunur
    @Value("${blogapp.expires.in}")
    private  long EXPIRES_IN;   // token nece saniye kecerllidir
    public String genereteJwtToken(Authentication auth){
        JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal();
        Date expiredDate =new Date(new Date().getTime()+EXPIRES_IN);  //indiki vaxtin ustine expires in gelir
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,APP_SECRET).compact();
    }
    Long getUserIdFromJwt (String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    boolean validateToken (String token){
        try{
            Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e){
            return false;
        } catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
