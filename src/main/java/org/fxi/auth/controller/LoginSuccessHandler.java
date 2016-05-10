package org.fxi.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fxi.auth.bean.reponse.AuthResponse;
import org.fxi.auth.entity.User;
import org.fxi.auth.entity.UserAuthority;
import org.fxi.auth.repo.UserRepository;
import org.fxi.auth.service.UserService;
import org.fxi.common.bean.reponse.Response;
import org.fxi.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class LoginSuccessHandler extends BaseController implements AuthenticationSuccessHandler {
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		AuthResponse respBody = new AuthResponse();
		
		UserDetails ud = (UserDetails) auth.getPrincipal();
		
		String email = ud.getUsername();
		User user = userService.getUserByEmail(email);

		String roles = "";
		for (GrantedAuthority role : ud.getAuthorities()) {
			roles += role.getAuthority()+",";
			if (UserAuthority.ROLE_USER.equals(role.getAuthority())) {
				request.getSession().setAttribute(SESSION_ATTR_USER, user);
			}
		}
		if (',' == roles.charAt(roles.length()-1)) {
			roles = roles.substring(0, roles.length()-1);
		}

		respBody.setUser(new AuthResponse.User(user.getUserId(), email, roles, user.getName()));
		respBody.setCode(Response.SUCCESS);
		respBody.setMsg(messageResource.getMessage("auth.login.successAs", new Object[] {email, roles}, locale));
		HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter();
		converter.write(respBody, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
	}

}
