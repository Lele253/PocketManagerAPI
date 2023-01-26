package apis.Manga.API.Security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;




@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    public String generateToken(String userEmail){
        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.DAYS);
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(Authentication authentication){
        User user= (User) authentication.getPrincipal();
        return generateToken(user.getUsername());
    }

    public String getUserMailFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean valideToken(String token){
            try{
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;

            }
            catch (SignatureException ex){
                log.error("Exception1");

            }
            catch (MalformedJwtException ex){
                log.error("Exception2");

            }
            catch (ExpiredJwtException ex){
                log.error("Exception3");

            }
            catch (UnsupportedJwtException ex){
                log.error("Exception4");

            }
            catch (IllegalArgumentException ex){
                log.error("Exception5");

            }
            return false;

    }
}
