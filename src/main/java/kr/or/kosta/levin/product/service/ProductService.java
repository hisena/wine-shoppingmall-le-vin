package kr.or.kosta.levin.product.service;

import java.util.Map;

import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.product.domain.Product;

/**
 * Product와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 박소연
 */
public interface ProductService {


	/** 상품 목록 및 페이징처리 */
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception;
	
	/**상품 상세 정보*/
	public Product detailProduct(String productId) throws Exception;
	
}
