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


//*.productId
//*.quantity
//email
//orderMoney
//receiverName
//receiverMobile
//addressId
//address
//detailedAddress
//zipCode
//deliveryComm


		OrderList orderList = new OrderList();
		List<OrderList> productList = new ArrayList<>();
		//String products = request.getParameter("Products");
		String products = "{\"products\" : [{\"productId\":\"2\", \"quantity\":\"2\"},{\"productId\":\"3\", \"quantity\":\"1\"} ], \"email\":\"sodus0131@naver.com\"}";
				
		JSONParser parser = new JSONParser();
		JSONObject productsObject;
		try {
			productsObject = (JSONObject) parser.parse(products);
			JSONArray productsList = (JSONArray) productsObject.get("products");
			for (Object object2 : productsList) {
				JSONObject productOne = (JSONObject)object2;
				orderList.setProductId((String)productOne.get("productId"));
				orderList.setQuantity((String)productOne.get("quantity"));
				productList.add(orderList);
			}
			productsObject.get("email");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		try {
			// 파라미터값 null 체크
			if (productId != null) {
				Product product = orderService.add(null, null, null, null);
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
			throw new ServletException("order/AddController 예외 ", e);
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		OrderList orderList = new OrderList();
		List<OrderList> productList = new ArrayList<>();
		//String products = request.getParameter("Products");
		String products = "{\"products\" : [{\"productId\":\"2\", \"quantity\":\"2\"},{\"productId\":\"3\", \"quantity\":\"1\"} ], \"email\":\"sodus0131@naver.com\"}";
				
		JSONParser parser = new JSONParser();
		JSONObject productsObject;
		try {
			productsObject = (JSONObject) parser.parse(products);			
			JSONArray productsList = (JSONArray) productsObject.get("products");
			for (Object object2 : productsList) {
				JSONObject productOne = (JSONObject)object2;
				System.out.println((String)productOne.get("productId"));
				orderList.setProductId((String)productOne.get("productId"));
				orderList.setQuantity((String)productOne.get("quantity"));
				productList.add(orderList);	
			}
			for (int i = 0; i < productList.size(); i++) {
				System.out.println(productList.get(i).get);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
}
