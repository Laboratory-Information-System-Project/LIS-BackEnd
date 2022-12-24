package com.douzone.lis_back.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter{
//
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request= (HttpServletRequest) req;
        // String host = "https://" + request.getServerName() + ":" +Integer.toString(request.getServerPort());
        response.setHeader("Access-Control-Allow-Origin", "http://lis-front-deploy-bucket.s3-website.ap-northeast-2.amazonaws.com");
        // response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, "
                + "Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers" +
                ", Access-Control-Allow-Methods, Access-Control-Allow-Origin, Access-Control-Allow-Credentials" +
                ",Access-Control-Allow-Headers");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {}
    public void destory() {}
}