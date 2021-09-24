package tech.vtsign.authservice.filters;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import tech.vtsign.authservice.Security.UserDetail;
//import tech.vtsign.authservice.Security.UserDetailService;
//import tech.vtsign.authservice.utils.Jwtutils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final UserDetailService userDetailService;
//    private final Jwtutils jwtTokenUtil;
//
//    @Value("${com.cxtuan.jwt.header}")
//    private String header;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String requestTokenHeader = request.getHeader("Authorization");
//        String route = request.getRequestURI();
//        boolean needsAuthentication = true;
//        if (route.startsWith("/api/auth")) {
//            needsAuthentication = false;
//        }
//
//        String username = null;
//        String jwtToken = null;
//        // JWT Token is in the form "Bearer token". Remove Bearer word and get
//        // only the Token
//        if (needsAuthentication) {
//            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//                System.out.println(requestTokenHeader);
//                jwtToken = requestTokenHeader.substring(7);
//                try {
//                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//                } catch (SignatureException e) {
//                    throw new SignatureException("Invalid JWT signature: {}");
//                } catch (MalformedJwtException e) {
//                    throw new MalformedJwtException("Invalid JWT token: {}");
//                } catch (UnsupportedJwtException e) {
//                    throw new UnsupportedJwtException("JWT token is unsupported: {}");
//                } catch (IllegalArgumentException e) {
//                    throw new IllegalArgumentException("JWT claims string is empty: {}");
//                } catch (ExpiredJwtException e) {
//                    System.out.println("Expired");
//                }
//
//            } else {
//                logger.warn("JWT Token does not begin with Bearer String");
//            }
//
//            // Once we get the token validate it.
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetail userDetails = (UserDetail) this.userDetailService.loadUserByUsername(username);
//
//                // if token is valid configure Spring Security to manually set
//                // authentication
//                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//
//                    usernamePasswordAuthenticationToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // After setting the Authentication in the context, we specify
//                    // that the current user is authenticated. So it passes the
//                    // Spring Security Configurations successfully.
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//            chain.doFilter(request, response);
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}
