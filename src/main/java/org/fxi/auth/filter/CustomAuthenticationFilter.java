package org.fxi.auth.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.fxi.auth.bean.request.AuthRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        AuthRequest loginRequest = this.getAuthRequest(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private AuthRequest getAuthRequest(HttpServletRequest request) {
        BufferedReader reader = null;
        AuthRequest loginRequest = null;
        try {
            reader = request.getReader();
            ObjectMapper mapper = new ObjectMapper();
            loginRequest = mapper.readValue(reader, AuthRequest.class);
        } catch (IOException ex) {
            Logger.getLogger(AbstractAuthenticationProcessingFilter.class.getName()).log(Level.INFO, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractAuthenticationProcessingFilter.class.getName()).log(Level.INFO, null, ex);
            }
        }

        if (loginRequest == null) {
            loginRequest = new AuthRequest();
        }

        return loginRequest;
    }
}
