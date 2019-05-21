package com.team.bookTicket.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.team.bookTicket.pojo.Customer;

public class MyInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url=request.getRequestURL().toString();
		System.out.println("url:"+url);		
		//先获得用户
		HttpSession session=request.getSession();
		Customer user=(Customer) session.getAttribute("user");
		if(url.matches(".+\\..+") || url.endsWith("/")) {//访问静态文件
			return true;
		}else {//访问接口
			String uri=request.getRequestURI();
			System.out.println("uri:"+uri);
			//访问CustomerController接口需要登录
			if(uri.startsWith("/bookTicket/customer")) {
				if(user!=null) {
					return true;
				}else {
					return false;
				}
			}
			//访问AdminController接口需要登录且为管理员
			if(uri.startsWith("/bookTicket/admin")) {
				if(user!=null && user.getIsAdmin()!=null) {
					return true;
				}else {
					return false;
				}
			}
			//其他情况，如登录,注销
			return true;
		}
	}
}
