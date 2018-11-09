package kr.or.kosta.levin.product.dao;

import java.util.List;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * User 정보와 DB연동을 위한 인터페이스
 * @author 류세은, 박소연
 *
 */
public interface ProductDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception;
	
	
}
