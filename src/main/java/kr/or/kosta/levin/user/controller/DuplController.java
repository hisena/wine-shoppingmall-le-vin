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
import kr.or.kosta.levin.user.service.UserService;

/**
 * 이메일 중복확인 기능을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/email-duplicate-check")
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
		Map<String, String> isContained;
		Map<String, String> result = new HashMap<String, String>();
		try {
			//email의 null 혹은 공백값 체크
			if (email != null && email.trim().length() != 0) {
				isContained = userService.emailDuplicate(email);
				System.out.println(isContained);
				//email이 DB에 존재할 경우(중복인 경우) - depleResult가 true
				if (isContained != null) {
					result.put("dupleResult", "true");
					return result;
				//email이 DB에 존재하지 않을 경우(중복된 경우가 아닐 경우) - depleResult가 false
				} else {
					result.put("dupleResult", "false");
					return result;
				}
			} else {
			//email에 null이나 공백값이 들어왔을 경우 - 400(bad request)
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("UserService.emailDuplicate() 예외 발생", e);
		}
	}
}
