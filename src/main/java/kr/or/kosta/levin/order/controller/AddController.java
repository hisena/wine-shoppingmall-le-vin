package kr.or.kosta.levin.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.HandlerBase;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.order.domain.Delivery;
import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.order.domain.OrderList;
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


		OrderList orderList;
		Order order = new Order();
		Address address = new Address();
		Delivery delivery = new Delivery();
		List<OrderList> productList = new ArrayList<>();
		//String products = request.getParameter("Products");
		String products = "{\"products\" : [{\"productId\":\"2\", \"quantity\":\"2\"},{\"productId\":\"3\", \"quantity\":\"1\"} ], \"email\":\"test0001@naver.com\","
				+ "\"orderMoney\":\"240000\", \"receiverName\":\"박소연\", \"receiverMobile\":\"010-1234-5678\", \"addressId\":\"null\", \"address\":\"서울시 종로구 종로동\", "
				+ "\"detailedAddress\":\"123-56 행복빌딩 901호\", \"zipCode\":\"55-666\", \"deliveryComm\":\"부재시 경비실에 부탁드립니다.\" }";
//		String products = "{\"products\" : [{\"productId\":\"4\", \"quantity\":\"2\"},{\"productId\":\"5\", \"quantity\":\"1\"} ], \"email\":\"test0001@naver.com\","
//				+ "\"orderMoney\":\"240000\", \"receiverName\":\"박소연\", \"receiverMobile\":\"010-1234-5678\", \"addressId\":\"1\", \"address\":\"null\", "
//				+ "\"detailedAddress\":\"null\", \"zipCode\":\"null\", \"deliveryComm\":\"부재시 경비실에 부탁드립니다.\" }";
		
		
		JSONParser parser = new JSONParser();
		JSONObject productsObject;
		try {
			productsObject = (JSONObject) parser.parse(products);
			JSONArray productsList = (JSONArray) productsObject.get("products");
			for (int i = 0; i < productsList.size(); i++) {
				
				JSONObject productOne = (JSONObject) productsList.get(i);
				orderList = new OrderList();
				orderList.setProductId((String)productOne.get("productId"));
				orderList.setQuantity((String)productOne.get("quantity"));
				productList.add(i, orderList);
			}
			
			order.setEmail((String)productsObject.get("email"));
			order.setOrderMoney((String)productsObject.get("orderMoney"));
			delivery.setAddressId((String)productsObject.get("addressId"));
			delivery.setReceiverName((String)productsObject.get("receiverName"));
			delivery.setReceiverMobile((String)productsObject.get("receiverMobile"));
			delivery.setDeliveryComments((String)productsObject.get("deliveryComm"));
			address.setAddressId((String)productsObject.get("addressId"));
			address.setAddress((String)productsObject.get("address"));
			address.setDetailedAddress((String)productsObject.get("detailedAddress"));
			address.setZipCode((String)productsObject.get("zipCode"));
			address.setEmail((String)productsObject.get("email"));
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		boolean addResult = false;
		System.out.println(order.toString());
		System.out.println(productList.toString());
		System.out.println(delivery.toString());
		System.out.println(address.toString());
		
		Map<String, String> map = new HashMap<>();
		try {
				addResult = orderService.add(order, delivery, address, productList);
				// 검색해온 상품상세정보가 이 null이 아니면
				if (addResult) {
					// 주문한 정보 다시 갖고 오기
					map.put("addResult", "true");
					return map;
				} else {
					// null일 경우
					throw new RequestUnauthorizedException();
				}
			
		} catch (Exception e) {
			throw new ServletException("order/AddController 예외 ", e);
		}
	}
	
	
}
