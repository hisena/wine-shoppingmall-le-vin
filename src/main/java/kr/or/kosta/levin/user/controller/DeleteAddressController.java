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
 * 마이페이지에서 신규배송지 삭제 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/address-info-delete")
public class DeleteAddressController implements Controller {

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
		String addressId = request.getParameter("address_id");
		
		Map<String, String> map = new HashMap<String, String>();
		boolean deleteAddressResult;
		
		try {
			// 파라미터값 null 유효성 검사
			if (addressId != null) { // addressId에 올바른 값이 들어오는 경우 - 서비스 메소드 실행
				deleteAddressResult = userService.deleteAddress(addressId);
				if (deleteAddressResult) { // 주소 삭제 성공했을 경우
					map.put("deleteAddressResult", "true");
				} else {
					// 실패했을 경우
					map.put("deleteAddressResult", "false");
				}
				return map;
			} else {
				// addressId 값으로 null이나 공백값이 들어왔을 경우 - 400(bad request) 에러 발생
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("OrderDeleteAddressController 예외 발생", e);
		}
	}
}
