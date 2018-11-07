package kr.or.kosta.levin.user.dao;

import kr.or.kosta.levin.user.domain.Address;


/**
 * Address 정보와 DB연결
 * DAO 패턴을 적용하기 위한 인터페이스
 * @author 박소연
 *
 */
public interface AddressDao {
	
	public boolean create(Address address) throws Exception;

}
