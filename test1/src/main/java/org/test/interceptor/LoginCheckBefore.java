package org.test.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class LoginCheckBefore extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*System.out.println("Login check prehandle..............");*/

		boolean useSession = 
				request.getSession().getAttribute("userId") != null?true:false;
		
		if(useSession) {
			/*System.out.println("current user use session ");*/
			return true;
		}
		
		Cookie loginCookie = WebUtils.getCookie(request, "userId");
		
		boolean useCookie = loginCookie != null?true:false;
		
		if(useCookie) {
			/*System.out.println("current user use cookie...");*/
			String cookieValue = loginCookie.getValue();
			System.out.println(cookieValue);
			request.getSession().setAttribute("userId", cookieValue);
			
			return true;
		}
		
		response.sendRedirect("/tomcat/login");
		
		return false;
		
	}

}
