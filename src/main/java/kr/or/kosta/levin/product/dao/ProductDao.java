package kr.or.kosta.levin.product.dao;

import java.util.List;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface ProductDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception;
	public int countBySearch(SearchPagination searchPagination) throws Exception;
	
	
}
