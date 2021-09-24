package tech.vtsign.authservice.utils;


import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import io.jsonwebtoken.Claims;

import org.slf4j.LoggerFactory;
import tech.vtsign.authservice.security.UserDetailPriciple;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;


@Component
public class Jwtutils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${com.cxtuan.jwt.expired}")
    private long expiredToken = 100;
//    @Value("${com.cxtuan.jwt.secret}")
    private String secretKey = "secret";
//    @Value("${com.cxtuan.jwt.refreshexpired}")
    private long expiredRefreshToken= 100;
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);

    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateAccessToken(UserDetailPriciple userDetailsPriciple) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetailsPriciple.getUsername());
    }
    public String generateAccessTokenUsername(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }


    public String generateRefreshToken(UserDetailPriciple userDetailPriciple) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetailPriciple.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + this.expiredRefreshToken * 1000))
                .signWith( this.key)
                .compact();
    }





    //validate token
    public Boolean validateToken(String token, UserDetailPriciple userDetailsPriciple) {
        String username = "";

        username = getUsernameFromToken(token);

        return (username.equals(userDetailsPriciple.getUsername()) && !isTokenExpired(token));
    }
}


