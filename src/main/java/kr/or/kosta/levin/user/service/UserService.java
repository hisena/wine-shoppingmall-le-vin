package kr.or.kosta.levin.user.service;

import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.exception.RequestPreconditionFailedException;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;

/**
 * User와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은, 박소연
 */
public interface UserService {

	/** 회원 검색 */
	public User search(String id) throws Exception;

	/** 회원 목록 */
	public List<User> list() throws Exception;

	/** 로그인 */
	public Map<String, String> login(String email, String passwd) throws Exception;

	/** 회원가입 */
	public boolean join(User user, Address address) throws Exception;

	/** 이메일 중복확인 */
	public Map<String, String> emailDuplicate(String email) throws Exception;

	/** 회원정보 수정 */
	public boolean changeInfo(User user) throws Exception;
	
	/** 회원 기본정보 목록 */
	public User listBasicInfo(String email) throws Exception;
	
	/** 신규 배송지 추가 */
	public boolean addAddress(Address address) throws Exception, RequestPreconditionFailedException;
	
}
