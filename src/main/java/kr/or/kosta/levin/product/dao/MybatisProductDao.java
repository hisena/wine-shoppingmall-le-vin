package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.product.domain.FilterPagination;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisProductDao implements ProductDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.product.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 상품 목록 불러오기(페이징, 검색처리)
	@Override
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception {
		List<Product> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPage", searchPagination);
		sqlSession.close();
		return list;
	}

	// 검색한 상품 목록 갯수
	@Override
	public int countBySearch(SearchPagination searchPagination) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE + "countBySearch", searchPagination);
		sqlSession.close();
		return count;
	}

	// 상품 상세
	@Override
	public Product getProduct(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Product product = sqlSession.selectOne(NAMESPACE + "getProduct", productId);
		sqlSession.close();
		return product;
	}

	// 상품 문의글 목록
	@Override
	public List<ProductQna> listByPageQna(Map<String, String> param) throws Exception {
		List<ProductQna> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPageQna", param);
		sqlSession.close();
		return list;
	}

	// 상품 문의글 갯수
	@Override
	public int countBySearchQna(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE + "countBySearchQna", productId);
		sqlSession.close();
		return count;
	}

	// 상품 문의 댓글 목록
	@Override
	public List<ProductQnaComment> listQnaComment(Map<String, String> param) throws Exception {
		List<ProductQnaComment> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listQnaComment", param);
		sqlSession.close();
		return list;
	}


	// 구매후기글 리스트
	@Override
	public List<Review> reviewListByPage(Map<String, String> parameter) throws Exception {
		List<Review> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "reviewListByPage", parameter);
		sqlSession.close();
		return list;

	}

	// 구매후기글 갯수
	@Override
	public int reviewCountBySearch(Map<String, String> parameter) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE + "reviewCountBySearch", parameter);
		sqlSession.close();
		return count;
	}

	// 구매후기글 상세보기
	@Override
	public Review readReview(int reviewId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Review review = null;
		review = sqlSession.selectOne(NAMESPACE + "readReview", reviewId);
		sqlSession.close();
		return review;
	}

	// 구매후기글 등록
	@Override
	public boolean createReview(Review review) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int result = sqlSession.insert(NAMESPACE + "createReview", review);
		boolean flag = false;
		// insert에 성공했으면
		if (result == 1) {
			// 커밋해주기
			sqlSession.commit();
			flag = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}

	
	//전체글 상품 문의글 작성
	@Override
	public boolean createQna(ProductQna productQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int insertResult = sqlSession.insert(NAMESPACE + "createQna", productQna);
		boolean createQnaResult = false;
		// insert에 성공했으면
		if (insertResult == 1) {
			// 커밋해주기
			sqlSession.commit();
			createQnaResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return createQnaResult;
	}
	
	/** 필터 초기화를 위해 각 값의 범위를 불러옴 */
	@Override
	public List<String> readKindValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<String> result = null;
		result = sqlSession.selectList(NAMESPACE + "readKindValues");
		sqlSession.close();
		return result;
	}
	
	@Override
	public List<Map<String, String>> readRegionValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Map<String, String>> result = null;
		result = sqlSession.selectList(NAMESPACE + "readRegionValues");
		sqlSession.close();
		return result;
	}
	
	@Override
	public Map<String, String> readAlcoholMinMaxValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> result = null;
		result = sqlSession.selectOne(NAMESPACE + "readAlcoholMinMaxValues");
		sqlSession.close();
		return result;
	}
	
	@Override
	public Map<String, String> readSugarContentMinMaxValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> result = null;
		result = sqlSession.selectOne(NAMESPACE + "readSugarContentMinMaxValues");
		sqlSession.close();
		return result;
	}
	
	@Override
	public Map<String, String> readBodyMinMaxValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> result = null;
		result = sqlSession.selectOne(NAMESPACE + "readBodyMinMaxValues");
		sqlSession.close();
		return result;
	}
	
	@Override
	public Map<String, String> readPriceMinMaxValues() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> result = null;
		result = sqlSession.selectOne(NAMESPACE + "readPriceMinMaxValues");
		sqlSession.close();
		return result;
	}
	
	// 필터와 페이지네이션 적용된 리스트 
	@Override
	public List<Product> filteredList(FilterPagination filterPagination) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Product> result = null;
		result = sqlSession.selectList(NAMESPACE + "readFilteredList", filterPagination);
		sqlSession.close();
		return result;
	}
	
	@Override
	public int filteredListCount(FilterPagination filterPagination) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		result = sqlSession.selectOne(NAMESPACE + "readFilteredListCount", filterPagination);
		sqlSession.close();
		return result;
	}
}
