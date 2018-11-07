package kr.or.kosta.levin.user.dao;

import kr.or.kosta.levin.user.domain.Address;


/**
 * Address 정보와 DB연동을 위한 인터페이스
 * @author 박소연
 *
 */
public interface AddressDao {
	
	/** 회원가입시 주소 등록*/
	public boolean create(Address address) throws Exception;

}
