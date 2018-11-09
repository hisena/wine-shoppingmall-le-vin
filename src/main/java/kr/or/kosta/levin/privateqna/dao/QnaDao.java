package kr.or.kosta.levin.privateqna.dao;

import java.util.List;

import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * 1:1 문의 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 류세은
 * 
 */
public interface QnaDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<PrivateQna> listByPage(SearchPagination searchPagination) throws Exception;
	/** 검색해온 상품 목록의 개수 반환*/
	public int countBySearch(SearchPagination searchPagination) throws Exception;
	
}
