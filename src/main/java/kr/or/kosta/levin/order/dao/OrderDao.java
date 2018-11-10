package kr.or.kosta.levin.order.dao;

import java.util.List;

import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * 주문 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface OrderDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 주문 목록 반환 */	
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception;
	/** 검색해온 주문 목록의 개수 반환*/
	public int countBySearch(SearchPagination searchPagination) throws Exception;
	/** 주문 상세*/
	public Product getProduct(String id) throws Exception;
	
	/** 주문하기*/
	public boolean create(Order order) throws Exception;
	
}
