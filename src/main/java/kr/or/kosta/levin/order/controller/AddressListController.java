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
 * 배송지 선택(목록불러오기) 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/order/address")
public class AddressListController implements Controller {

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

		String email = request.getParameter("email");
		
		List<Address> list;
		Map<String, Object> map = new HashMap<>();
		try {
			if(email != null) {
				list = orderService.addressList(email);
				if(list != null) {
					map.put("AddressInfo", list);
					return map;
				}else {
					throw new RequestUnauthorizedException();				}
			}else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("AddressListService 예외 발생", e);
		}
	}
}
