package kr.or.kosta.levin.order.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.order.domain.Order;

/**
 * 주문 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface OrderDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 주문 목록 반환 */	
	public List<Map<String, String>> listByPage(Map<String, String> param) throws Exception;
	/** 검색해온 주문 목록의 개수 반환*/
	public int countByList(String email) throws Exception;
	/** 주문 상세 - 주문 정보 및 배송정보*/
	public Map<String, String> getOrder(Map<String, String> param) throws Exception;
	/** 주문 상세 - 상품 정보*/
	public List<Map<String, String>> getProduct(String orderId) throws Exception;
	/** 주문하기*/
	public boolean create(Order order) throws Exception;
	/** 주문 취소*/
	public boolean deleteOrder(String orderId) throws Exception;
}
