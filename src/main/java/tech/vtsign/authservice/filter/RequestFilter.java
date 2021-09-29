//package tech.vtsign.authservice.filter;
//
//import io.jsonwebtoken.MalformedJwtException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import tech.vtsign.authservice.exception.TokenExpiredException;
//import tech.vtsign.authservice.exception.TokenMissingException;
//import tech.vtsign.authservice.util.JwtUtil;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
////public class RequestFilter extends OncePerRequestFilter {
//public class RequestFilter implements Filter {
//    private final JwtUtil jwtUtil;
//
////    @Override
////    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        String path = request.getRequestURI();
////
////        List<String> accessUrls = List.of("/auth/login", "/auth/register", "/auth/refresh-token", "/auth/jwt");
////        if (!moveOn(accessUrls, path)) {
////            String auth = request.getHeader("Authorization");
////            if (auth == null || !auth.startsWith("Bearer ")) {
////                throw new TokenMissingException("Must add header token Authorization Bearer: YOUR_TOKEN");
////
////            } else {
////                String accessToken = auth.substring("Bearer ".length());
////                if (jwtUtil.isTokenExpired(accessToken)) {
////                    throw new TokenExpiredException("Token is expired");
////                }
////            }
////        }
////        filterChain.doFilter(request, response);
////    }
//
//    private boolean moveOn(List<String> accessUrls, String url) {
//        return accessUrls.contains(url);
//    }
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        String path = req.getRequestURI();
//
//        List<String> accessUrls = List.of("/auth/login", "/auth/register", "/auth/refresh-token", "/auth/jwt");
//
//
//        if (!moveOn(accessUrls, path)) {
//            String auth = req.getHeader("Authorization");
//            if (auth == null || !auth.startsWith("Bearer ")) {
//                throw new TokenMissingException("Must add header token Authorization Bearer: YOUR_TOKEN");
//
//            } else {
//                String accessToken = auth.substring("Bearer ".length());
//                if (jwtUtil.isTokenExpired(accessToken)) {
//                    throw new TokenExpiredException("Token is expired");
//                }
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
