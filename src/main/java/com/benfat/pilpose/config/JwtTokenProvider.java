package com.benfat.pilpose.config;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.benfat.pilpose.controllers.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {


    private String jwtSecret = "ddUKlQ32jg8zRKG4AwFHAHAHAHAGFIGUIGIL";


    public String generateToken(UserDto authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setSubject(authentication.getUsername())
                .claim("email", authentication.getEmail())
                .claim("userName", authentication.getUsername())
                .claim("idUser", authentication.getIdUser())
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return (Long) claims.get("id");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // Log error
        } catch (MalformedJwtException ex) {
            // Log error
        } catch (ExpiredJwtException ex) {
            // Log error
        } catch (UnsupportedJwtException ex) {
            // Log error
        } catch (IllegalArgumentException ex) {
            // Log error
        }
        return false;
    }
}
