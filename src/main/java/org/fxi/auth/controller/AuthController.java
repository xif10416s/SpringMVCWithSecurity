package org.fxi.auth.controller;

import org.fxi.common.bean.reponse.Response;
import org.fxi.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



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
