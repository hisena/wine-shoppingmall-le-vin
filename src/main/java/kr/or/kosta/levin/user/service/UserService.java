package kr.or.kosta.levin.user.service;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;

/**
 * User와 관련된 비즈니스 로직 수행을 위한 인터페이스
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

}
