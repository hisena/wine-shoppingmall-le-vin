package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import kr.or.kosta.levin.common.domain.SearchPagination;

import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.product.domain.FilterPagination;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;
import kr.or.kosta.levin.product.domain.ReviewComment;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 박소연, 류세은
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
	/** 구매후기글 등록 */
	public boolean createReview(Review review) throws Exception;

	/** 상품 문의글 등록 */
	public boolean createQna(ProductQna productQna) throws Exception; 
	
	/** 상품 문의 댓글 등록 */
	public boolean createQnaComment(ProductQna productQna) throws Exception; 
	
	/** 구매후기글 수정 */
	public boolean updateReview(Review review) throws Exception;
	
	/** 구매후기글 삭제 */
	public boolean deleteReview(int reviewId) throws Exception;

	/** 구매후기글의 댓글 등록 */
	public boolean createReviewComment(Map<String, String> parameter) throws Exception; 

	/** 상품문의글 상세보기 */
	public ProductQna readQna(String qnaId) throws Exception;
	

	/** 상품문의글 수정하기 */
	public boolean updateQna(ProductQna productQna) throws Exception;

	/** 구매후기글의 댓글리스트 */
	public List<ReviewComment> listReviewComment(int parentId) throws Exception;
	
	/** 구매후기글의 댓글 수정 */
	public boolean updateReviewComment(Map<String, Object> parameter) throws Exception;

	/** 상품문의 댓글 수정하기 */
	public boolean updateQnaComment(ProductQna productQna) throws Exception;
	
	/** 상품문의글 및 댓글 삭제하기 */
	public boolean deleteQna(String productId) throws Exception;
	
	/** 구매후기글의 댓글 삭제 */
	public boolean deleteReviewComment(int childId) throws Exception;
	
	/** 필터 초기화를 위해 각 값의 범위를 불러옴 */
	public List<String> readKindValues() throws Exception;
	public List<Map<String, String>> readRegionValues() throws Exception;
	public Map<String, String> readAlcoholMinMaxValues() throws Exception;
	public Map<String, String> readSugarContentMinMaxValues() throws Exception;
	public Map<String, String> readBodyMinMaxValues() throws Exception;
	public Map<String, String> readPriceMinMaxValues() throws Exception;
	
	/** 필터에 따른 상품 목록 (페이지네이션 적용) */	
	public List<Product> filteredList(FilterPagination filterPagination) throws Exception;
	public int filteredListCount(FilterPagination filterPagination) throws Exception;
}
