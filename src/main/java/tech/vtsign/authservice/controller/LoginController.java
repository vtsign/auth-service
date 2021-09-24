package tech.vtsign.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vtsign.authservice.security.UserDetailServicePriciple;
import tech.vtsign.authservice.jwtmodel.JwtRespone;
import tech.vtsign.authservice.service.JwtService;
import tech.vtsign.authservice.utils.Jwtutils;

@RestController
public class LoginController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private Jwtutils jwtutils;
    @Autowired
    private UserDetailServicePriciple userDetailServicePriciple;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser() {

        JwtRespone jwt=null;
        try {
            jwt = jwtService.authenticate();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name or password not found");
        }

        return ResponseEntity.ok(jwt);
    }
//
//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshJWTRequest request) {
//        ResponeJWTRefresh responeJWTRefresh = null;
//        try {
//            responeJWTRefresh = jwtService.refreshToken(request);
//            System.out.println(responeJWTRefresh.getRefreshToken());
//        }catch (SignatureException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JWT signature: {}");
//        } catch (MalformedJwtException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JWT token: {}");
//        } catch (UnsupportedJwtException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT token is unsupported: {}");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT claims string is empty: {}");
//        } catch (ExpiredJwtException e) {
//            System.out.println("dsjfsdbfbsdbfjhsdbjh");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("JWT expired");
//        }
//        return ResponseEntity.ok(responeJWTRefresh);
//
//    }
}
