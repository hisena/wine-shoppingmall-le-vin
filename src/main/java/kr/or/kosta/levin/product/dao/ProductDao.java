package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.common.web.Params;
import kr.or.kosta.levin.user.domain.User;

/**
 * User 정보와 DB연동을 위한 인터페이스
 * @author 류세은, 박소연
 *
 */
public interface ProductDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<User> listByPage(Params params) throws Exception;
	
	/** 검색유형, 검색값에 따른 사용자 개수 반환 - 페이징 처리 시 필요 */	
	public int countBySearch(Params params) throws Exception;
	
}
