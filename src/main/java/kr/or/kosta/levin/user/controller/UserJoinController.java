package kr.or.kosta.levin.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;
import kr.or.kosta.levin.user.service.UserService;


@Bean(type=BeanType.Controller)
@RequestMapping(value="/user/join")
public class UserJoinController implements Controller {
	
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
			throws ServletException {
		
		Map<String, String> joinResult= null;
		User user = new User();

		Address address = new Address();
		try {
			joinResult = userService.join(user, address);
		} catch (Exception e) {
			throw new ServletException("UserService.list() 예외 발생", e);
		}
		
		return joinResult;

	}

}
