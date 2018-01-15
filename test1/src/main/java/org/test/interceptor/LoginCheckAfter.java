package org.test.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.test.dto.UserDTO;

public class LoginCheckAfter extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Map<String, Object> map = modelAndView.getModel();
		
		/*System.out.println(map+"세션드러오나?");*/
		
		if(map.get("userId") != null) {
			String auto = request.getParameter("auto");
			
			UserDTO dto = (UserDTO) map.get("userId");
			
			request.getSession().setAttribute("userId", dto.getUid());
			/*System.out.println("세션셋"+dto);*/
			
			try {
				if(auto.equals("on")) {
					
					System.out.println("use Session");
					//use cookie
					
					System.out.println(dto);
					Cookie loginCookie = new Cookie("userId", dto.getUid());
					loginCookie.setMaxAge(60*60*24);
					response.addCookie(loginCookie);
				
					System.out.println("use Cookie");
					
					}
				}
			catch (Exception e) {
				System.out.println("not have");
			}
		}/*else if(map.get("userId") == null){
			response.sendRedirect("/tomcat/logout");
		}*/
		
	}

}
