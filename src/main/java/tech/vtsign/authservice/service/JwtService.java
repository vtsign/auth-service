package tech.vtsign.authservice.service;


//import com.cxtuan.springboottutorial.ConfigAuthent.UserDetailPriciple;
//import com.cxtuan.springboottutorial.ConfigAuthent.UserDetailServicePriciple;
//import com.cxtuan.springboottutorial.Controller.JWT.LoginRequest;
//import com.cxtuan.springboottutorial.jwt.JWTUtils;
//import com.cxtuan.springboottutorial.jwt.RefreshJWTRequest;
//import com.cxtuan.springboottutorial.jwt.ResponeJWTRefresh;
//import com.cxtuan.springboottutorial.jwt.jwtRespone;
//import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import tech.vtsign.authservice.security.UserDetailPriciple;
import tech.vtsign.authservice.security.UserDetailServicePriciple;
import tech.vtsign.authservice.jwtmodel.JwtRespone;
import tech.vtsign.authservice.utils.Jwtutils;

@Service
public class JwtService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private Jwtutils jwtUtils;

    @Autowired
    private UserDetailServicePriciple userDetailServicePriciple;

    public JwtRespone authenticate() throws BadCredentialsException {
           UserDetailPriciple userDetailsPriciple = getUserDetail();
//       UserDetailPriciple userDetails = (UserDetailPriciple) userDetailServicePriciple.loadUserByUsername(loginRequest.getUsername());

       String jwt = jwtUtils.generateAccessToken(userDetailsPriciple);
//        String refreshToken = jwtUtils.generateRefreshToken(userDetails);
//
//        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return new JwtRespone(jwt);

    }

//    public ResponeJWTRefresh refreshToken(RefreshJWTRequest request) throws ExpiredJwtException, SignatureException, UnsupportedJwtException, IllegalArgumentException, MalformedJwtException {
//        String jwt = null;
//        String requestRefreshToken = null;
//
//        requestRefreshToken = request.getRefreshToken();
//        String username = jwtUtils.getUsernameFromToken(requestRefreshToken);
//        System.out.println(username);
//        UserDetailPriciple userDetails = (UserDetailPriciple) userDetailService.loadUserByUsername(username);
//
//        if (jwtUtils.validateToken(requestRefreshToken, userDetails)) {
//            jwt = jwtUtils.generateAccessToken(userDetails);
//        }
//
//        return new ResponeJWTRefresh(jwt, requestRefreshToken);
//
//    }

    private UserDetailPriciple getUserDetail(/*LoginRequest loginRequest*/) {
        //throw BadCridential if username password invalid
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetailPriciple userDetails = (UserDetailPriciple) authentication.getDetails();
        UserDetailPriciple userDetailsPriciple = (UserDetailPriciple) userDetailServicePriciple.loadUserByUsername("cxtuan");
        return userDetailsPriciple;
    }

}

