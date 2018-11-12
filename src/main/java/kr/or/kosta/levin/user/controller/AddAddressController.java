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
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;
import kr.or.kosta.levin.user.service.UserService;

/**
 * 마이페이지에서 신규배송지 추가 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/user/address-info-add")
public class AddAddressController implements Controller {

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
		String address = request.getParameter("address");
		String zipCode = request.getParameter("zipCode");
		String detailedAddress = request.getParameter("detailedAddress");
		
//		String email = "abcde1234@naver.com";
//		String address = "서울시 강동구 암사3동 롯데캐슬퍼스트 1차";
//		String zipCode = "06-456";
//		String detailedAddress = "128동 1702호";

		
		Address addressInfo = new Address();
		Map<String, String> map = new HashMap<String, String>();
		boolean addAddressResult;

		// Address 객체에 클라이언트로부터 받은 정보 입력
		addressInfo.setEmail(email);
		addressInfo.setAddress(address);
		addressInfo.setZipCode(zipCode);
		addressInfo.setDetailedAddress(detailedAddress);
		
		try {
			// 파라미터값 null 유효성 검사
			if (addressInfo.checkNull(addressInfo)) { // Address 객체에 올바른 값이 들어오는 경우 - 서비스 메소드 실행
				addAddressResult = userService.addAddress(addressInfo);
				if (addAddressResult) { // 신규 주소 추가 성공했을 경우
					map.put("addAddressResult", "true");
				} else {
					// 실패했을 경우 - 401 반환
					map.put("addAddressResult", "false");
				}
				return map;
			} else {
				// user 객체의 속성값으로 null이나 공백값이 들어왔을 경우 - 400(bad request) 에러 발생
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("userService.changeResult() 예외 발생", e);
		}
	}
}
