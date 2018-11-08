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

/**
 * 로그인 기능을 위한 세부 컨트롤러
 * @author 박소연
 *
 */
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

		// 회원가입 결과 변수 선언
		boolean joinResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<>();
		// 화면에서 받은 파라미터값 처리
		User user = new User();
		Address address = new Address();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setMobile(request.getParameter("mobile"));
		user.setUserName(request.getParameter("userName"));
		address.setEmail(request.getParameter("email"));
		address.setAddress(request.getParameter("address"));
		address.setDetailedAddress(request.getParameter("detailedAddress"));
		address.setZipCode(request.getParameter("zipCode"));

		try {
			// 파라미터값 null 유효성 검사
			if (user.checkNull(user) && address.checkNull(address)) {
				joinResult = userService.join(user, address);
				// 회원 가입 성공시
				if (joinResult) {
					map.put("joinResult", "true");
				} else {
				// 실패시
					map.put("joinResult", "false");
				}
				return map;
			}else {
				// 파라미터 값이 null값일 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("UserService.join() 예외 발생", e);
		}
	}

}
