package com.df.support.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.df.support.pojo.Admin;
import com.df.support.utils.Auth;
import com.df.support.utils.Constants;
import com.df.support.utils.DwzRes;

public class AccessInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(handler instanceof ResourceHttpRequestHandler){
			return true;
		}
		if(handler instanceof DefaultServletHttpRequestHandler){
			return true;
		}
		if (handler instanceof HandlerMethod) {
			// ignore
			HandlerMethod hm = (HandlerMethod)handler;
			Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
			if(auth == null || auth.value() == null || auth.value().trim().isEmpty()){
				return true;
			}
//			if(hm.getBean().getClass() == FrontAction.class){
//				return true;
//			}
		}
		if(request.getRequestURI().endsWith("login")){
			return true;
		}
		Admin admin = null;
		if((admin = (Admin)request.getSession().getAttribute ("admin")) == null){
			//response.setStatus(301);
			if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
		        PrintWriter out = response.getWriter();
		        DwzRes res = DwzRes.newInstance().r(DwzRes.TIMEOUT).msg("Session Timeout! Please re-sign in!");
		        out.println(res.toJSON());
		    }else{
		    	String login = request.getServletContext().getContextPath() + "/mgr/login.html";
				response.sendRedirect(login);
		    }
			return false;
		}
		if(admin.id == 0){
			return true;
		}
		//return true;
		if (handler instanceof HandlerMethod) {
			Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
			if(auth == null || auth.value() == null || auth.value().trim().isEmpty()){
				return true;
			}
			// 登录了检查,方法上只是@Auth,表示只要求登录就能通过.@Auth("authority")这类型,验证用户权限
			@SuppressWarnings("unchecked")
			Map<String, String> perms = (Map<String, String>) request.getSession().getAttribute(Constants.ADMIN_PERMISSION);
			if (!perms.containsKey(auth.value())) {
				DwzRes res = DwzRes.newInstance();
				res.r(DwzRes.ERROR).msg("ERROR:NO " + auth.value());
				PrintWriter out = response.getWriter();
				out.println(res.toJSON());
				out.flush();
				out.close();
				return false;
			}
		}
		return true;
	}

}
