package kr.or.kosta.levin.user.dao;

import kr.or.kosta.levin.user.domain.Address;


/**
 * Address 정보와 DB연동을 위한 인터페이스
 * @author 박소연
 *
 */
public interface AddressDao {
	
	/** 회원가입시 주소 등록 & MyPage에서 신규 배송지 추가*/
	public boolean create(Address address) throws Exception;
	
	/** 주소 중복 확인 */
	public boolean certify(Address address) throws Exception;
	
	/** 배송지 삭제 */
	public boolean delete(String addressId) throws Exception;

}
