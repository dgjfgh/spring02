package com.panpom.springmvc01.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.panpom.springmvc01.dao.User1Dao;
import com.panpom.springmvc01.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.panpom.springmvc01.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/")
public class UserController extends BaseController{
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private User1Dao user1Dao;

    @ResponseBody
    @RequestMapping(value = "/testsqlxml", method = {RequestMethod.POST, RequestMethod.GET})
    public Object testSqlxml(String uname) {
        System.out.println(uname);
        ArrayList<User> list = user1Dao.list();
        return list;
    }

    @ResponseBody
    @RequestMapping("/testsqlanno")
    public Object testSqlAnno(String name) {
        ArrayList<User> users = userDao.selectUser(4);
        return users;
    }

    @RequestMapping(value = "/index1")
    public ModelAndView index1() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", "xxx");
        modelAndView.addObject("age", "20");
        return modelAndView;
    }

    /**
     * resetFul
     */
    @RequestMapping(value = "/index3/{id}a")
    public Object index3(@PathVariable String id) {
        System.out.println(request.getMethod());
        System.out.println(id);
        return "index";
    }

    @RequestMapping("/loginpage")
    public String loginPage(String uname) {
        return "login";
    }

    @RequestMapping("/login")
    public Object tt(String name, String pwd, HttpServletResponse response) throws IOException {
        System.out.println(name);
        System.out.println(pwd);
        session.setAttribute("user", new User(1, name));
        response.sendRedirect("main");
        return null;
    }

    @RequestMapping("/main")
    public Object main() {
        return "main";
    }

    @RequestMapping("/testCookie")
    @ResponseBody
    public String gotCookie(HttpServletResponse response) throws IOException {
        String returnStr = "";
        String LAST_ACCESS_TIME = "time";
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (LAST_ACCESS_TIME.equals(cookie.getName())) {
                String time = cookie.getValue();
                returnStr = "time=" + time;
            }
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = year + "/" + (month + 1) + "/" + date + " " + hour + ":" + minute + ":" + second;

        Cookie cookie = new Cookie(LAST_ACCESS_TIME, time);
        cookie.setMaxAge(300);//单位秒
        response.addCookie(cookie);
        return returnStr;
    }

}
