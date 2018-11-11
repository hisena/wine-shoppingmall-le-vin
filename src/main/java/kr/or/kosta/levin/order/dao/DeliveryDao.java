package kr.or.kosta.levin.order.dao;

import kr.or.kosta.levin.order.domain.Delivery;

/**
 * 배송 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface DeliveryDao {
	
	/** 주문에 따른 배송 정보 등록하기*/
	public boolean create(Delivery delivery) throws Exception;
	
}
