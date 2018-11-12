package kr.or.kosta.levin.order.controller;

import java.util.HashMap;
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
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.order.service.OrderService;

/**
 * 주문목록 불러오기 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/order/list")
public class ListController implements Controller {

	// 서비스 선언
	@Inject
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, RequestException {

		// 클라이언트로부터 받은 값
//		String email = request.getParameter("email");
//		String currentPage = request.getParameter("currentPage");
//		
		String email = "test0001@naver.com";
		String currentPage = "1";
		
		Map<String, String> param = new HashMap<>();
		Map<String, Object> result;
		try {
			// null값이 들어오지 않았을 경우
			if(email != null && currentPage != null) {
				//파라미터로 보낼 값을 map에 저장
				param.put("email", email);
				param.put("currentPage", currentPage);
				// list메소드 호출
				result = orderService.list(param);
				// 데이터 목록이 잘 왔을 경우
				if(result != null) {
					return result;
				}else {
					// 데이터가 없을 경우
					throw new RequestUnauthorizedException();				}
			}else {
				// null값 들어왔을 경우
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("OrderListController 예외 발생", e);
		}
	}
}
