package kr.or.kosta.levin.order.controller;

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
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 상품 상세보기를 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/order/add")
public class AddController implements Controller {

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

		String productId = request.getParameter("productId");
		try {
			// 파라미터값 null 체크
			if (productId != null) {
				Product product = productService.detailProduct(productId);
				// 검색해온 상품상세정보가 이 null이 아니면
				if (product != null) {
					// front controller에 넣기
					return product;
				} else {
					// null일 경우
					throw new RequestUnauthorizedException();
				}
			// 파라미터 값이 null일 경우
			} else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/DetailController 예외 ", e);
		}
	}
}
