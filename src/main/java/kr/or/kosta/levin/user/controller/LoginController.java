package kr.or.kosta.levin.user.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.user.service.UserService;

@Bean(type=BeanType.Controller)
@RequestMapping(value="/user/login")
public class LoginController implements Controller {
	
	@Inject
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, RequestException {
		
		Map<String, String> loginInfo = null;
		try {
			loginInfo = userService.login("abcde1234@naver.com","abcde1234");
			if(loginInfo == null) {
				throw new RequestUnauthorizedException();
			}else {
				return loginInfo;
			}
		} catch (Exception e) {
			throw new ServletException("UserService.login() 예외 발생", e);
		}
		
		

	}

}
