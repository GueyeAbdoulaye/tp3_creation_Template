package com.postgreSql.demo.Config;

import com.postgreSql.demo.Repository.UserRepository;
import com.postgreSql.demo.Service.JWTService;
import com.postgreSql.demo.model.Role;
import com.postgreSql.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder().id(1l).username("user").password(passwordEncoder().encode("user")).role(Role.USER)
                .build();

        UserDetails admin = User.builder().id(2l).username("admin").password(passwordEncoder().encode("admin")).role(Role.ADMIN)
                .build();

        UserDetails guest = User.builder().id(3l).username("guest").password(passwordEncoder().encode("guest")).build();

        return new InMemoryUserDetailsManager(user, admin, guest);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsService());
        authprovider.setPasswordEncoder(passwordEncoder());
        return authprovider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
}
