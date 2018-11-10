package kr.or.kosta.levin.order.dao;

import java.util.Map;

import kr.or.kosta.levin.order.domain.OrderList;

/**
 * 주문 목록 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface OrderListDao {
	
	/** 주문하기*/
	public boolean create(Map<String, String> productInfo) throws Exception;
}
