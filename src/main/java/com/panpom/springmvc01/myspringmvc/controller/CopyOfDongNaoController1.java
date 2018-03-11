package com.panpom.springmvc01.myspringmvc.controller;

import com.panpom.springmvc01.myspringmvc.annotion.Controller;
import com.panpom.springmvc01.myspringmvc.annotion.Qualifier;
import com.panpom.springmvc01.myspringmvc.annotion.RequestMapping;
import com.panpom.springmvc01.myspringmvc.service.DongNaoService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("dongnao1")
public class CopyOfDongNaoController1 {
	
	@Qualifier("dongNaoServiceImpl")
	private DongNaoService service;
	
	@RequestMapping("insert")
	public static String insert(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.getWriter().write("dfghjk1");
		return "asd";
	} 
}
