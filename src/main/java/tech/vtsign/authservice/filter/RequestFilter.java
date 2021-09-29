package tech.vtsign.authservice.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.vtsign.authservice.exception.TokenExpiredException;
import tech.vtsign.authservice.exception.TokenMissingException;
import tech.vtsign.authservice.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestFilter implements Filter {
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();

        List<String> accessUrls = List.of("/auth/login", "/auth/register", "/auth/refresh-token", "/auth/jwt");


        if (!moveOn(accessUrls, path)) {
            String auth = req.getHeader("Authorization");
            if (auth == null || !auth.startsWith("Bearer ")) {
                throw new TokenMissingException("Must add header token Authorization Bearer: YOUR_TOKEN");
            } else {
                String accessToken = auth.substring("Bearer ".length());
                if (jwtUtil.isTokenExpired(accessToken)) {
                    throw new TokenExpiredException("Token is expired");
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean moveOn(List<String> accessUrls, String url) {
        return accessUrls.contains(url);
    }
}
