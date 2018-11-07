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

		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setMobile(request.getParameter("mobile"));
		user.setUserName(request.getParameter("UserName"));
		address.setEmail(request.getParameter("email"));
		address.setAddress(request.getParameter("address"));
		address.setDetailedAddress(request.getParameter("detailedAddress"));
		address.setZipCode(request.getParameter("zipCode"));

		try {
			if (user.checkNull(user) && address.checkNull(address)) {
				joinResult = userService.join(user, address);
				if (joinResult) {
					map.put("joinResult", "true");
				} else {
					map.put("joinResult", "false");
				}
				return map;
			}else {
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("UserService.join() 예외 발생", e);
		}
	}

}
