package kr.or.kosta.levin.order.dao;

import kr.or.kosta.levin.order.domain.Address;

/**
 * 배송지 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface AddressDao {
	
	/** 신규 배송지 등록하기*/
	public boolean create(Address address) throws Exception;
}
