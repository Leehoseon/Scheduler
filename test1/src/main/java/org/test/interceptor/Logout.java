package org.test.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class Logout extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String member = (String) session.getAttribute("userId");
		
		if(member != null) {
			session.removeAttribute("userId");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "userId");
			
			if(loginCookie != null) {
				
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
			}
		
		}
		
		return true;
		
	}

}
