package com.panpom.springmvc01.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.panpom.springmvc01.dao.User1Dao;
import com.panpom.springmvc01.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.panpom.springmvc01.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/")
public class UserController  {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDao userDao;
	@Autowired
	private User1Dao user1Dao;

	@RequestMapping("/test")
	@ResponseBody
	public Object test(String uname){
		System.out.println(uname);
		ArrayList<User> list = user1Dao.list();
		return list;
	}

	@RequestMapping("/index")
	public Object gettest2(String name){

		ArrayList<User> users = userDao.selectUser(5);
		ModelAndView modelAndView=new ModelAndView("index");
		modelAndView.addObject("user",users);
		return "index";
	}

	@RequestMapping(value="/index1")
    public ModelAndView index1(){  
        ModelAndView modelAndView = new ModelAndView("index");  
        modelAndView.addObject("name", "xxx");  
        modelAndView.addObject("age", "20");  
        return modelAndView;  
    } 
	
	/** 
     *   resetFul
     */  
    @RequestMapping(value = "/index3/{id}a")
    public Object index3(@PathVariable String id){
		System.out.println(request.getMethod());
		System.out.println(id);
		return "index";
	}

	@RequestMapping("/loginpage")
	public String loginPage(String uname){
		return "login";
	}

	@RequestMapping("/login")
	public Object tt(String name,String pwd,HttpServletResponse response) throws IOException {
		System.out.println(name);
		System.out.println(pwd);
		session.setAttribute("name",name);
		session.setAttribute("pwd",pwd);
		response.sendRedirect("main");
		return null;
	}
	@RequestMapping("/main")
	public Object main(){
		return "main";
	}

}
