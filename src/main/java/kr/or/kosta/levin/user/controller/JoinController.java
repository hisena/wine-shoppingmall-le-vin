package kr.or.kosta.levin.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;
import kr.or.kosta.levin.user.service.UserService;

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/join")
public class JoinController implements Controller {

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

		boolean joinResult;
		Map<String, String> map = new HashMap<>();
		User user = new User();
		Address address = new Address();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userName = request.getParameter("UserName");
		String mobile = request.getParameter("mobile");
		String addre = request.getParameter("address");
		String detailedAdd = request.getParameter("detailedAddress");
		String zipCode = request.getParameter("zipCode");
		//String email = null;

		if (email != null && password != null && userName != null
				&& mobile != null && addre != null && detailedAdd != null && zipCode != null && email.trim().length() != 0 && password.trim().length() != 0 && userName.trim().length() != 0
				&& mobile.trim().length() != 0 && addre.trim().length() != 0 && detailedAdd.trim().length() != 0
				&& zipCode.trim().length() != 0) {

			user.setEmail(email);
			user.setPassword(password);
			user.setMobile(mobile);
			user.setUserName(userName);
			address.setEmail(email);
			address.setAddress(addre);
			address.setDetailedAddress(detailedAdd);
			address.setZipCode(zipCode);
		} else {
			throw new RequestBadRequestException();
		}

		/*
		 * user.setEmail("test0003@naver.com"); user.setPassword("test0003");
		 * user.setUserName("서버테"); user.setMobile("010-3333-3333");
		 * address.setAddress("서울시 테삼구 테삼동 333-33");
		 * address.setEmail("test0003@naver.com");
		 * address.setDetailedAddress("이노플렉스 303호"); address.setZipCode("33-333");
		 */

		try {

			joinResult = userService.join(user, address);
			if (joinResult) {
				map.put("joinResult", "true");
			} else {
				map.put("joinResult", "false");
			}
			return map;

		} catch (Exception e) {
			throw new ServletException("UserService.join() 예외 발생", e);
		}
	}

}
