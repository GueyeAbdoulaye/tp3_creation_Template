package com.postgreSql.demo.Service;

import com.postgreSql.demo.Controller.AuthenticationRequest;
import com.postgreSql.demo.Controller.AuthenticationResponse;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RunWith(EasyMockRunner.class)
public class AuthentificationServiceTest extends TestCase {

    @Mock
    private AuthenticationManager authenticationManagerMock;
    @Mock
    private JWTService jwtServiceMock;

    public AuthentificationServiceTest (){

    }

    @TestSubject
    private AuthentificationService testSubject = new AuthentificationService(authenticationManagerMock,jwtServiceMock);

    @Test
    public void authenticate(){
        AuthenticationRequest request = new AuthenticationRequest();
            request.setUsername("Test");
            request.setPassword("Test");

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "Test";
            }

            @Override
            public String getUsername() {
                return "Test";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };


        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return "Token";
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };

        //Mock
        EasyMock.expect(authenticationManagerMock.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())))
                .andReturn(authentication);

        EasyMock.expect(jwtServiceMock.generateToken(userDetails)).andReturn("Token");

        //when

        AuthenticationResponse reponse  =  testSubject.authentification(request);

        //then
        Assert.assertNotNull(reponse);
        Assert.assertEquals("Token",reponse );

    }
}
