package kr.or.kosta.levin.order.service;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.order.domain.Delivery;
import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.order.domain.OrderList;

/**
 * 주문과 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 박소연
 */
public interface OrderService {


	/** 주문 목록 및 페이징처리 */
	public Map<String, Object> list(Map<String, String> param) throws Exception;
	
	/** 주문 상세 정보*/
	public Map<String, Object> detail(Map<String, String> param) throws Exception;

	/** 주문 하기 */
	public boolean add(Order order, Delivery delivery, Address address, List<OrderList> productList) throws Exception;
	
	/** 배송지 목록 불러오기*/
	public List<Address> addressList(String email) throws Exception;
	
	/** 주문취소하기*/
	public boolean cancel(String orderId) throws Exception;
	
	/** 반품 하기*/
	public boolean refund(String orderId) throws Exception;
	
}
