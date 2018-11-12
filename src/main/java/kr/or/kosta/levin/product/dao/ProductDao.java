package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연
 *
 */
public interface ProductDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception;
	/** 검색해온 상품 목록의 개수 반환*/
	public int countBySearch(SearchPagination searchPagination) throws Exception;
	/** 상품 상세*/
	public Product getProduct(String id) throws Exception;
	/** 페이징 처리에 따른 상품문의글 목록 반환 */	
	public List<ProductQna> listByPageQna(Map<String, String> param) throws Exception;
	/** 검색해온 상품 문의글 목록의 개수 반환*/
	public int countBySearchQna(String productId) throws Exception;
	/** 상품댓글 목록 반환 */	
	public List<ProductQnaComment> listQnaComment(Map<String, String> param) throws Exception;
	/** 구매후기 목록*/
	public List<Review> reviewListByPage(Map<String, String> parameter) throws Exception;
	/** 검색해온 구매후기글 갯수 */
	public int reviewCountBySearch(Map<String, String> parameter) throws Exception;
	/** 구매후기글 상세보기 */
	public Review readReview(int reviewId) throws Exception;
}
