package org.fxi.common.controller;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	
	public final static String BASE_URI = "/spring/";
    public final static String SESSION_ATTR_USER = "SESSION_ATTR_USER";

	protected final static Locale locale = Locale.CHINA;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	@ModelAttribute
	public void initFileds(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	@Resource protected ResourceBundleMessageSource messageResource;
	@Autowired
	protected MessageSource messageSource;
	

	public Set<String> getAuthorities() {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return AuthorityUtils.authorityListToSet(ud.getAuthorities());
	}
}
