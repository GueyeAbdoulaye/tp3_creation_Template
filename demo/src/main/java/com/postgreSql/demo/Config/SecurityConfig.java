package com.postgreSql.demo.Config;

import com.postgreSql.demo.model.Role;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled=true, jsr250Enabled = true)
//@EnableMethodSecurity(securedEnabled=true, jsr250Enabled = true)
// securedEnabled
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthentificationFilter jwtAuthentificationFilter;
    private final AuthenticationProvider authentificationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/login/**", "/error")
                .permitAll()
                /*.requestMatchers(HttpMethod.POST, "/api/v1/players")
                .hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/v1/players")
                .hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/players")
                .hasRole(Role.ADMIN.name())*/
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authentificationProvider)
                //.addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
                .addFilterBefore(jwtAuthentificationFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
}
