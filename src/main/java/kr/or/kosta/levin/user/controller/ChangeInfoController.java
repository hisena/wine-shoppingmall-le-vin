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
 * 회원정보 수정 기능을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/info-change")
public class ChangeInfoController implements Controller {

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

		// 클라이언트로부터 받는 회원정보
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String mobile = request.getParameter("mobile");

		Map<String, String> map = new HashMap<String, String>();
		boolean changeResult = false;

		try {
			// 파라미터값 null 유효성 검사
			// newPassword는 비밀번호 변경하지 않을 경우 기존 비밀번호 이용하므로 null 가능
			if (email == null
				|| password == null
				|| mobile == null) { 
				throw new RequestBadRequestException();
			}
				
			// 기존 비밀번호가 틀릴 경우
			if (userService.login(email, password) == null) {
				throw new RequestUnauthorizedException();
			}	
			
			// user 객체에 클라이언트로부터 받은 정보 입력
			User user = new User();
			user.setEmail(email);
			// 새로운 비밀번호가 입력되었을 경우에만 새로운 비밀번호로 값을 설정
			if (newPassword == null || newPassword.trim().length() <= 0) {
				user.setPassword(password);
			} else {
				user.setPassword(newPassword);
			}
			user.setMobile(mobile);
			
			changeResult = userService.changeInfo(user);
			if (changeResult) { // 회원정보 수정에 성공했을 경우 - 클라이언트에게 changeResult : true 반환
				map.put("changeResult", "true");
			} else {
				// 실패했을 경우 - 클라이언트에게 changeResult : false 반환
				map.put("changeResult", "false");
			}
			return map;
			
		} catch (Exception e) {
			throw new ServletException("userService.changeResult() 예외 발생", e);
		}
	}
}
