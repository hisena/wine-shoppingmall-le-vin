package kr.or.kosta.levin.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.common.web.Params;
import kr.or.kosta.levin.user.domain.User;

/**
<<<<<<< HEAD
 * Dao 패턴 적용을 위한 인터페이스 선언
=======
 * Address 정보와 DB연동을 위한 인터페이스
>>>>>>> branch 'develop' of https://github.com/leeseungeun/wine-shoppingmall-le-vin.git
 * @author 류세은, 박소연
 *
 */
public interface UserDao {
	
	/** 회원 가입시 회원 기본 정보 등록*/
	public boolean create(User user) throws Exception;
	
	public User read(String id) throws Exception;
	
	public void update(User user) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public List<User> listAll() throws Exception;
	
	public Map<String, String> certifyEmail(String email) throws Exception;
	
	/** 로그인 */
	public Map<String, String> certify(String email, String passwd) throws Exception;
	
	public List<Map<String, String>> employeeList() throws Exception;
	
	/** 선택페이지에 따른 사용자 목록 반환 */	
	public List<User> listByPage(int page) throws Exception;
	
	/** 선택페이지, 조회 목록개수에 따른 사용자 목록 반환 */	
	public List<User> listByPage(int page, int listSize) throws Exception;
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<User> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception;
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<User> listByPage(Params params) throws Exception;
	
	/** 검색유형, 검색값에 따른 사용자 개수 반환 - 페이징 처리 시 필요 */	
	public int countBySearch(String searchType, String searchValue) throws Exception;
	
	public int countBySearch(Params params) throws Exception;
	
}
