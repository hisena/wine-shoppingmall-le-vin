package kr.or.kosta.levin.order.controller;

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
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.order.service.OrderService;

/**
 * 주문상세보기 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/order/cancel")
public class CancelController implements Controller {

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
		//String orderId = request.getParameter("orderId");

		String orderId = "2"; 
		Map<String, String> map = new HashMap<>();
		boolean cancelResult;
		try {
			// null값이 들어오지 않았을 경우
			if(orderId != null) {
				
				// cancel 메소드 호출
				cancelResult = orderService.cancel(orderId);
				// delete가 잘 처리 되었을 경우
				if(cancelResult) {
					map.put("cancelResult", "true");
					return map;
				}else {
					// 처리 안 됐을 경우
					throw new RequestUnauthorizedException();				}
			}
			else {
				// null값 들어왔을 경우
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("OrderListController 예외 발생", e);
		}
	}
}
