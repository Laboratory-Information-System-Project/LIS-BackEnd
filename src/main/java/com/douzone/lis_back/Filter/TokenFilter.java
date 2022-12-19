package com.douzone.lis_back.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
@WebFilter()
public class TokenFilter implements Filter {

    private final Environment getToken;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(!"/user-service/login".equals(req.getRequestURI())) {
            if (req.getHeader("Authorization").isEmpty()) {

                log.error("not authorized");

                resp.sendError(401);

            }

            String authorizationHeader = req.getHeader("Authorization");
            String jwt = authorizationHeader.replace("Bearer ", "").trim();

            if (!isJwtValid(jwt)) {
                resp.sendError(401);
            }
        }

        chain.doFilter(req,response);

    }



    private boolean isJwtValid(String jwt) {
       // Key secretKey = Keys.hmacShaKeyFor(getToken.getProperty("jwt.secret").getBytes(StandardCharsets.UTF_8));
        boolean isValue = true;
        String subject = null;
        try {
//            subject = Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
//                    .build()
//                    .parseClaimsJws(jwt)
//                    .getBody()
//                    .getSubject();

            Algorithm algorithm = Algorithm.HMAC256(getToken.getProperty("jwt.secret").getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(jwt);
            subject = decodedJWT.getSubject();
        } catch (Exception ex){
            isValue = false;
        }
        if(subject == null || subject.isEmpty()){
            isValue = false;
        }
        return isValue;
    }
}
