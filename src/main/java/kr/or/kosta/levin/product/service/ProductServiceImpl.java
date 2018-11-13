package kr.or.kosta.levin.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.domain.Pagination;
import kr.or.kosta.levin.common.domain.PaginationManager;
import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.product.dao.ProductDao;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;

/**
 * Product와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Service)
public class ProductServiceImpl implements ProductService {

	// dao 선언
	@Inject
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	// dao getter/setter
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 상품 목록
	@Override
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception {

		Map<String, Object> map = new HashMap<>();

		// 페이징, 검색 처리된 상품 목록
		List<Product> list = productDao.listByPage(searchPagination);
		// 검색해온 상품 목록 갯수
		int count = productDao.countBySearch(searchPagination);
		// 페이징 관련정보(시작, 끝 페이지 등) 처리
		PaginationManager pm = new PaginationManager();
		pm.setPagination(searchPagination);
		pm.setTotalCount(count);

		// controller로 넘겨 주기 위해 map에 담아주기
		map.put("productList", list);
		map.put("pageInfo", pm.pageInfo());

		if (searchPagination.getSearchKeyword() != null) {
			map.put("searchPagination", searchPagination);
		}
		return map;
	}

	// 상품 상세 보기
	@Override
	public Product detailProduct(String productId) throws Exception {
		return productDao.getProduct(productId);
	}

	// 상품 문의글 목록
	@Override
	public Map<String, Object> listQna(Map<String, String> param) throws Exception {
		Map<String, Object> map = new HashMap<>();

		// 페이징, 검색 처리된 상품 문의글 목록
		List<ProductQna> list = productDao.listByPageQna(param);
		// 검색해온 상품문의글 목록 갯수
		int count = productDao.countBySearchQna(param.get("productId"));
		// 페이징 관련정보(시작, 끝 페이지 등) 처리
		Pagination pagination = new Pagination();
		PaginationManager pm = new PaginationManager();
		pagination.setCurrentPage(Integer.parseInt(param.get("currentPage")));
		pagination.setPerPageNum(5);
		pm.setPagination(pagination);
		pm.setTotalCount(count);

		// 상품 문의글 목록이 존재하지 않을 경우
		if (list.isEmpty()) {
			map.put("qnaList", "false");
		} else {
			map.put("qnaList", list);
		}
		map.put("pageInfo", pm.pageInfo());

		return map;
	}

	// 상품 문의 댓글 목록
	@Override
	public List<ProductQnaComment> listQnaComment(Map<String, String> param) throws Exception {

		return productDao.listQnaComment(param);
	}

	// 구매후기글 리스트
	@Override
	public Map<String, Object> listReview(Map<String, String> parameter) throws Exception {
		int currentPage = Integer.parseInt(parameter.get("currentPage"));
		int perPageNum = Integer.parseInt(parameter.get("perPageNum"));
		Map<String, Object> map = new HashMap<String, Object>();

		// 페이징, 검색 처리된 구매후기 리스트
		List<Review> list = productDao.reviewListByPage(parameter);
		// 검색해온 구매후기글 갯수
		int count = productDao.reviewCountBySearch(parameter);
		// 페이징 관련정보(시작, 끝 페이지 등) 처리
		PaginationManager pm = new PaginationManager();
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currentPage);
		pagination.setPerPageNum(perPageNum);
		pm.setPagination(pagination);
		pm.setTotalCount(count);

		// controller로 넘겨 주기 위해 map에 담아주기
		if (list.isEmpty()) {
			map.put("reviewList", false);
		}
		map.put("reviewList", list);
		map.put("pageInfo", pm.pageInfo());
		return map;
	}

	// 구매후기글 상세보기
	@Override
	public Review detailReview(int reviewId) throws Exception {
		Review review = productDao.readReview(reviewId);
		return review;
	}

	// 구매후기글 등록
	@Override
	public boolean addReview(Review review) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean addResult = false;
		boolean flag = false;
		// 구매후기글 등록
		addResult = productDao.createReview(review);
		// 등록에 성공하면
		if (addResult) {
			flag = true;
		}
		return flag;
	}

	// 상품 문의글 등록
	@Override
	public boolean addQna(ProductQna productQna) throws Exception {

		return productDao.createQna(productQna);

	}

	// 상품 문의 댓글 등록
	@Override
	public boolean addQnaComment(ProductQna productQna) throws Exception {

		return productDao.createQnaComment(productQna);
	}
	
	// 구매후기글 수정
	@Override
	public boolean editReview(Review review) throws Exception {
		boolean result = false;
		boolean flag = false;
		// 구매후기글 수정
		result = productDao.updateReview(review);
		// 구매후기글 수정 성공여부 확인
		if (result) { // 수정에 성공했을 경우
			flag = true;
		}
		return flag;
	}
	
	// 구매후기글 삭제
	@Override
	public boolean removeReview(int reviewId) throws Exception {
		boolean result = false;
		boolean flag = false;
		// 구매후기글 삭제
		result = productDao.deleteReview(reviewId);
		// 구매후기글 삭제 성공여부 확인
		if (result) { // 삭제에 성공했을 경우
			flag = true;
		}
		return flag;
	}
	
	// 구매후기글의 댓글 등록
	@Override
	public boolean addReviewComm(Map<String, String> parameter) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
				boolean addResult = false;
				boolean flag = false;
				// 구매후기글의 댓글 등록
				addResult = productDao.createReviewComm(parameter);
				// 등록에 성공하면
				if (addResult) {
					flag = true;
				}
				return flag;
	}
}
