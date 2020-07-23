package com.example.demo.filter;

import com.example.demo.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.hibernate.service.spi.ServiceException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "jwtFilter", urlPatterns = "/hello/*")
public class HTTPBearerAuthorizeAttribute implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if("options".equals(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String auth = httpRequest.getHeader("Authorization");
        if(auth != null && auth.length() > 7) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo("bearer") == 0)
            {
                auth = auth.substring(7, auth.length());
                Claims claims = JWTUtils.parseJWT(auth);
                if(claims != null) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        // token 校验失败，抛出异常
        throw new ServiceException("token校验失败");
    }

    @Override
    public void destroy() {
    }

}
