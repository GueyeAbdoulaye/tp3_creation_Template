package com.postgreSql.demo.Config;

import com.postgreSql.demo.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken ;
        final String username ;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        // why 7 : because the length of "Bearer " is 7
        jwtToken = authHeader.substring(7);
        username = jwtService.extractUsername(jwtToken);
        if(username != null){
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

           if(jwtService.isTokenValid(jwtToken, userDetails)){
               SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
           }
        }
        filterChain.doFilter(request,response);
    }
}
