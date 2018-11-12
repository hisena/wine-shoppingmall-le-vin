package kr.or.kosta.levin.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import kr.or.kosta.levin.user.service.UserService;

/**
 * 비밀번호 찾기에서 인증번호 확인하고 비밀번호 업데이트를 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/password-find")
public class FindPasswordController implements Controller {

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
		
		boolean findPasswordResult;
		Map<String, String> map = new HashMap<>();
		Map<String, String> param = new HashMap<>();
		// 클라이언트에서 받은 정보
		String email = request.getParameter("email");
		String valiNumber = request.getParameter("validation_number");
		String password = request.getParameter("password");
		
		try {
			// 파라미터값 null 유효성 검사
			if (email != null && valiNumber != null && password != null) { 
				// email과 validationNum, password가 올바른 값이 들어오는 경우 - 서비스 메소드 실행
				param.put("email", email);
				param.put("valiNumber", valiNumber);
				param.put("password", password);
				
				findPasswordResult = userService.findPassword(param);
				if (findPasswordResult) { 
					map.put("findPasswordResult", "true");
				} else {
					map.put("findPasswordResult", "false");
				}
				return map;
			} else {
				// email, valiNumber, password 값으로 null이나 공백값이 들어왔을 경우 - 400(bad request) 에러 발생
				throw new RequestBadRequestException();
				
			}
		} catch (Exception e) {
			throw new ServletException("userService.changeResult() 예외 발생", e);
		}
	}
}
