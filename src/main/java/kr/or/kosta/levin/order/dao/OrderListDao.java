package kr.or.kosta.levin.order.dao;

import java.util.Map;

/**
 * 주문 목록 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface OrderListDao {
	
	/** 주문목록 등록*/
	public boolean create(Map<String, String> productInfo) throws Exception;
}
