package com.prueb.tecnica.paises.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueb.tecnica.paises.security.CustomerDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    private String userName = null;

    Claims claims = null;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (request.getServletPath().matches("/api/user/login")) {
            log.info("Login en proceso");
            filterChain.doFilter(request, response);
        } else {
            log.info("Autorizacion en proceso "+request.getServletPath());
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                userName = jwtUtil.extratUserName(token);
                claims = jwtUtil.extratAllClaims(token);
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customerDetailsService.loadUserByUsername(userName);

                if (jwtUtil.valiateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    new WebAuthenticationDetailsSource().buildDetails(request);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        }
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        return userName;
    }
}
