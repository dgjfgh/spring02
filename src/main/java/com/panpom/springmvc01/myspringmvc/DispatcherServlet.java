package com.panpom.springmvc01.myspringmvc;

import com.panpom.springmvc01.myspringmvc.annotion.Controller;
import com.panpom.springmvc01.myspringmvc.annotion.Qualifier;
import com.panpom.springmvc01.myspringmvc.annotion.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DispatcherServlet extends HttpServlet {

	private List<String> pkgClazzs=new ArrayList<String>();//全类名
	//Control注解value和  类的实例一一对应的map
	private Map<String, Object> instanceMaps=new HashMap<String, Object>();
	//维护一个请求和类方法的对应关系
	private Map<String, Method> handlerMaps=new HashMap<String, Method>();
	
	@Override
	public void init() throws ServletException {
		String beanPath = "com.demo";
		try {
			//拿到全类名放在pkgClazzs
			scanBase(beanPath);
			//pkgClazzs的类实例化
			findBeansInstance();
			
			//注入实例
			springIoc();
			
			//方法链问题   网址用对应的方法处理
			handlerMaps();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handlerMaps() {
		if (instanceMaps.size()==0) {
			return;
		}
		
		for (Map.Entry<String, Object> entry : instanceMaps.entrySet()) {
			if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
				//拿到Controller注解和value
				String classUrl = ((Controller)entry.getValue().getClass().getAnnotation(Controller.class)).value();
				
				Method[] methods = entry.getValue().getClass().getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(RequestMapping.class)) {
						String methodUrl = ((RequestMapping)method.getAnnotation(RequestMapping.class)).value();
						//方法执行链
						handlerMaps.put("/"+classUrl+"/"+methodUrl, method);
						
					}
				}
				
				
			}
		}
	}

	private void springIoc() throws Exception {
		if (instanceMaps.size()==0) {
			return;
		}
		
		for (Map.Entry<String, Object> entry : instanceMaps.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Qualifier.class)) {
					String qualifierValue = ((Qualifier)field.getAnnotation(Qualifier.class)).value();
					
					//可以拿到private属性
					field.setAccessible(true);
					//实例set到field
					field.set(entry.getValue(), instanceMaps.get(qualifierValue));
				}
			}
		}
	}

	private void findBeansInstance() throws Exception {
		// TODO Auto-generated method stub
		if (pkgClazzs.size()==0) {
			return;
		}
		for (String className : pkgClazzs) {
			Class ccName=Class.forName(className.replace(".class", ""));
			if (ccName.isAnnotationPresent(Controller.class)) {
				Object controllerInstance = ccName.newInstance();
				//拿到注解上的value
				Controller controller = (Controller)ccName.getAnnotation(Controller.class);
				String controllValue = controller.value();
				instanceMaps.put(controllValue, controllerInstance);
				
			}
		}
	}

	private void scanBase(String beanPath) {
		URL url = this.getClass().getClassLoader()
				.getResource("/" + replacePath(beanPath));
		String pathFile = url.getFile();
		File file = new File(pathFile);
		String[] files = file.list();
		for (String path : files) {
			File eachFile = new File(pathFile + path);
			if (eachFile.isDirectory()) {
				// 递归
				scanBase(beanPath + "." + eachFile.getName());
			} else {
				pkgClazzs.add(beanPath + "." + eachFile.getName());
				System.out.println("扫描到的文件:   "+beanPath + "." + eachFile.getName());
			}
		}
	}

	private String replacePath(String beanPath) {
		return beanPath.replaceAll("\\.", "/");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();//网址
		String projectName = req.getContextPath();//项目路径
		String replace = uri.replace(projectName, "");//   "/dongnao/insert"
		Method method = handlerMaps.get(replace);
		
		if (method==null) {
			resp.getWriter().write("404");
			return;
		}
		
		//    "/dongnao/insert"
		String[] split = replace.split("/");
		String className=split[1];
		Object object = instanceMaps.get(className);
		
		try {
			System.out.println(method);
			System.out.println(object);
			method.invoke(object, new Object[]{req,resp});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
