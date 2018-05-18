package com.panpom.springmvc01.action;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by majianghua on 2018/5/18 0018.
 */
public class BaseController {

    public HttpServletRequest request;
    public HttpServletResponse response;
    public HttpSession session;

    @ModelAttribute
    public void gotModel(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

}
