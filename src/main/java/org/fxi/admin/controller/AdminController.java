package org.fxi.admin.controller;

import org.fxi.common.bean.reponse.Response;
import org.fxi.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xifei on 16-5-10.
 */
@Controller
@RequestMapping(value="/service/admin", method= RequestMethod.POST)
public class AdminController extends BaseController {

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public @ResponseBody
    Response logoutSuccess() {
        Response respBody = new Response();

        respBody.setCode(Response.SUCCESS);
        return respBody;
    }
}
