package org.fxi.auth.controller;

import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.fxi.common.bean.reponse.Response;
import org.fxi.common.controller.BaseController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping(value="/auth", method= RequestMethod.POST)
public class AuthController extends BaseController {


	@RequestMapping(value="/logout/success", method= RequestMethod.GET)
	public @ResponseBody
	Response logoutSuccess() {
		Response respBody = new Response();
		
		respBody.setCode(Response.SUCCESS);
		return respBody;
	}
	
	@RequestMapping(value="/access-denied", method= RequestMethod.GET)
	public @ResponseBody
	Response accessDenied() {
		Response respBody = new Response();
		respBody.setCode(Response.AUTH_FAILED);
		respBody.setMsg(messageResource.getMessage("advertisement.invalidRole", null, "Invalid Role", locale));
		return respBody;
	}

}
