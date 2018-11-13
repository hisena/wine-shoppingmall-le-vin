package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqna.domain.PrivateQnaComment;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.domain.Review;
import kr.or.kosta.levin.product.domain.ReviewComment;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스
 * 
 * @author 박소연, 류세은
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

	// 상품 문의글 등록
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

	// 상품문의 댓글 등록
	@Override
	public boolean createQnaComment(ProductQna productQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int insertResult = sqlSession.insert(NAMESPACE + "createQnaComment", productQna);
		boolean createQnaCommentResult = false;
		// 댓글 등록에 성공했으면
		if (insertResult == 1) {
			// 상품문의 원글과 댓글의 관계 설정해주기
			if (sqlSession.insert(NAMESPACE + "createQnaCommentId", Integer.parseInt(productQna.getQnaId())) == 1) {
				// 커밋해주기
				sqlSession.commit();
				createQnaCommentResult = true;
			} else {
				sqlSession.rollback();
			}
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return createQnaCommentResult;
	}

	// 구매후기글 수정
	@Override
	public boolean updateReview(Review review) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update(NAMESPACE + "updateReview", review);
		boolean flag = false;
		// update에 성공한 경우
		if (result == 1) {
			// 커밋
			sqlSession.commit();
			flag = true;
		} else {
			// 실패한 경우 rollback
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}

	// 구매후기글 삭제
	@Override
	public boolean deleteReview(int reviewId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update(NAMESPACE + "deleteReview", reviewId);
		boolean flag = false;
		// delete에 성공한 경우
		if (result == 1) {
			// 커밋
			sqlSession.commit();
			flag = true;
		} else {
			// 실패한 경우 rollback
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}


	// 구매후기글의 댓글 내용 등록
	@Override
	public boolean createReviewComm(Map<String, String> parameter) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String parentId = parameter.get("parentId");
		// insert문 실행 후 반환값 저장
		int insertResult = sqlSession.insert(NAMESPACE + "createReviewComm", parameter);
		boolean createReviewCommResult = false;
		// 댓글 등록에 성공했으면
		if (insertResult == 1) {
			// 구매후기 원글과 댓글의 관계 설정해주기
			if ((sqlSession.insert(NAMESPACE + "createReviewCommId", Integer.parseInt(parentId))) == 1) {
				// 커밋해주기
				sqlSession.commit();
				createReviewCommResult = true;
			} else {
				sqlSession.rollback();
			}
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return createReviewCommResult;
	}
	// 상품문의 상세보기
	@Override
	public ProductQna readQna(String qnaId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ProductQna productQna = sqlSession.selectOne(NAMESPACE + "readQna", qnaId);
		sqlSession.close();
		return productQna;

	}


	// 상품문의글 수정하기
	@Override
	public boolean updateQna(ProductQna productQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// update문 실행 후 반환값 저장
		int updateResult = sqlSession.update(NAMESPACE + "updateQna", productQna);
		boolean updateQnaResult = false;
		// update에 성공했으면
		if (updateResult == 1) {
			// 커밋해주기
			sqlSession.commit();
			updateQnaResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateQnaResult;
	}
	
	//구매후기글 댓글리스트
	@Override
	public List<ReviewComment> listReviewComm(int parentId) throws Exception {
		List<ReviewComment> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listReviewComm", parentId);
		sqlSession.close();
		return list;

	}

	// 상품문의 댓글 수정하기
	@Override
	public boolean updateQnaComment(ProductQna productQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// update문 실행 후 반환값 저장
		int updateResult = sqlSession.update(NAMESPACE + "updateQnaComment", productQna);
		boolean updateQnaCommResult = false;
		// update에 성공했으면
		if (updateResult == 1) {
			// 커밋해주기
			sqlSession.commit();
			updateQnaCommResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateQnaCommResult;
	}

	// 상품 문의글 및 댓글 삭제하기
	@Override
	public boolean deleteQna(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// update문 실행 후 반환값 저장
		int deleteResult = sqlSession.update(NAMESPACE + "deleteQna", productId);
		boolean deleteQnaResult = false;
		// update에 성공했으면
		if (deleteResult == 1) {
			// 커밋해주기
			sqlSession.commit();
			deleteQnaResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteQnaResult;
	}
}
