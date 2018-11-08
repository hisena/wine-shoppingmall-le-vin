package kr.or.kosta.levin.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.tools.web.BadHttpRequest;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.user.domain.User;
import kr.or.kosta.levin.user.service.UserService;

/**
 * 회원 기본정보 목록 확인을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/basic-info-list")
public class BasicInfoController implements Controller {

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

		// 클라이언트로부터 받는 이메일
		String email = request.getParameter("email");
		User user = null;
		try {
			// 파라미터값 null 유효성 검사
			if (email != null && email.trim().length() != 0) { // email이 null이 아닐 경우 - 서비스 메소드 실행
				 user = userService.listBasicInfo(email);
				if (user != null) { // 회원정보에 등록된 email값이 정상적으로 들어왔을 때 - return user
					return user;
				} else { // 회원정보에 등록되어 있지 않은 email값이 들어왔을 때 - 401 에러 발생
					throw new RequestUnauthorizedException();
				}
			} else {
				// email이 null이거나 공백값일 때 - 400(bad request) 에러 발생
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("userService.listBasicInfo() 예외 발생", e);
		}
	}
}
