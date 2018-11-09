package kr.or.kosta.levin.product.service;

import java.util.List;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * Product와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은, 박소연
 */
public interface ProductService {


	/** 상품 목록 */
	public List<Product> list(SearchPagination searchPagination) throws Exception;
	
}
