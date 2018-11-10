package kr.or.kosta.levin.order.service;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.order.domain.Delivery;
import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.order.domain.OrderList;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * 주문과 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 박소연
 */
public interface OrderService {


	/** 주문 목록 및 페이징처리 */
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception;
	
	/** 주문 상세 정보*/
	public Product detailProduct(String productId) throws Exception;

	/** 주문 */
	public boolean add(Order order, Delivery delivery, Address address, List<OrderList> productList) throws Exception;
}
