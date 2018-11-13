package kr.or.kosta.levin.product.service;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;

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
	
	/** 상품의 상품 문의글 목록 및 페이징 처리*/
	public Map<String, Object> listQna(Map<String, String> param) throws Exception;
	
	/** 상품의 상품 문의 댓글 목록*/
	public List<ProductQnaComment> listQnaComment(Map<String, String> param) throws Exception;

	/**구매후기글 리스트*/
	public Map<String, Object> listReview(Map<String, String> parameter) throws Exception;

	/**구매후기글 상세보기*/
	public Review detailReview(int reviewId) throws Exception;
	

	/**구매후기글 등록*/
	public boolean addReview(Review review) throws Exception;

	/** 상품의 상품 문의글 등록*/
	public boolean addQna(ProductQna productQna) throws Exception;
	

}
