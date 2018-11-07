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

/**
 * 이메일 중복확인 기능을 위한 세부 컨트롤러
 * @author 류세은
 */

@Bean(type=BeanType.Controller)
@RequestMapping(value="/user/email-duplicate-check")
public class DuplController implements Controller {
	
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
		
		String email = request.getParameter("email");
		Map<String, String> emailAvailable;
		try {
		if(email !=null && email.trim().length() != 0) {
			emailAvailable = userService.emailDuplicate("abcde1234@naver.com");
			return emailAvailable;
	      }else {
	    	  throw new RequestUnauthorizedException();
	      }
		} catch (Exception e) {
			throw new ServletException("UserService.emailDuplicate() 예외 발생", e);
		}
	}
}
