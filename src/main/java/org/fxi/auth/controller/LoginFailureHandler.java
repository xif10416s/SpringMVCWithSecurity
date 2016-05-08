package org.fxi.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fxi.auth.bean.reponse.AuthResponse;
import org.fxi.common.bean.reponse.Response;
import org.fxi.common.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;



public class LoginFailureHandler extends BaseController implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		AuthResponse respBody = new AuthResponse();
		
		if (exception instanceof BadCredentialsException)
			respBody.setMsg(messageResource.getMessage("auth.login.errorPassword", null, locale));
		else if (exception instanceof UsernameNotFoundException) {
			respBody.setMsg(messageResource.getMessage("auth.login.emailNotFound", new Object[] {""}, locale));
		} else {
			Logger logger = Logger.getLogger(SecurityContext.class);
			logger.warn(exception);
		}
		
		respBody.setCode(Response.AUTH_FAILED);
		
		HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter();
		converter.write(respBody, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
	}

}
